package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.request.UserUpdateRequest;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.mapper.UserMapper;
import com.UniCharity.UniCharity.entities.User;
import com.UniCharity.UniCharity.repositories.UserRepository;
import com.UniCharity.UniCharity.services.iservices.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
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
        return UserMapper.toUserResponse(user);
    }

    @Override
    public User createUserWithEmail(String email, String name) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRole("user");
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
                }
            }
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return user;
    }

    @Override
    public PageResponse<UserResponse> getUsers(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<UserResponse> userResponses = userRepository.findAll(sort).stream().map(UserMapper::toUserResponse).toList();

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userResponses.size());
        Page<UserResponse> usersPage = new PageImpl<>(userResponses.subList(start, end), pageable, userResponses.size());

        return new PageResponse<>(
                usersPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(usersPage.getTotalElements())
                        .currentPage(usersPage.getNumber())
                        .totalPages(usersPage.getTotalPages())
                        .pageSize(usersPage.getSize())
                        .build()
        );
    }

    @Override
    public UserResponse getUser(int userId) {
        return UserMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public UserResponse updateUser(int userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        UserMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return UserMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserStatus(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setStatus(false);
        return UserMapper.toUserResponse(userRepository.save(user));
    }
}
