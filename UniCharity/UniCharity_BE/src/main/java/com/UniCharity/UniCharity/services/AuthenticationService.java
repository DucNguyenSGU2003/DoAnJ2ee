package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.AuthenticationRequest;
import com.UniCharity.UniCharity.dto.request.IntrospectRequest;
import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.authentication.AuthenticationResponse;
import com.UniCharity.UniCharity.dto.response.authentication.IntrospectResponse;
import com.UniCharity.UniCharity.dto.response.authentication.RegisterResponse;
import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.entities.User;
import com.UniCharity.UniCharity.mapper.UserMapper;
import com.UniCharity.UniCharity.repositories.UserRepository;
import com.UniCharity.UniCharity.services.iservices.IAuthenticationService;
import com.UniCharity.UniCharity.utils.JwtUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verifierd = signedJWT.verify(verifier);
        return IntrospectResponse.builder().valid(verifierd && expityTime.after(new Date())).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authentication = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authentication) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        UserResponse userResponse = UserMapper.toUserResponse(user);
        var token = JwtUtils.generateToken(user);
        JwtUtils.addTokenToCookie(response, token);
        return AuthenticationResponse.builder().token(token).authenticated(true).user(userResponse).build();
    }

    @Override
    public RegisterResponse register(UserCreateRequest request, HttpServletResponse response) {
        User user = UserMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            // Kiểm tra xem ngoại lệ liên quan đến cột nào
            if (exception.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolation = (ConstraintViolationException) exception.getCause();
                String message = constraintViolation.getSQLException().getMessage();
                // Kiểm tra thông báo lỗi và phát hiện lỗi trùng cột nào
                if (message.contains("email")) {
                    throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
                } else if (message.contains("username")) {
                    throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
                }
            }
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        var token = JwtUtils.generateToken(user);
        JwtUtils.addTokenToCookie(response, token);
        return new RegisterResponse(UserMapper.toUserResponse(user), token);
    }
}
