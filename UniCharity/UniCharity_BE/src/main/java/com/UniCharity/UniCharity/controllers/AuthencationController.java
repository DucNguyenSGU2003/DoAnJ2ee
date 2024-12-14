package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.AuthenticationRequest;
import com.UniCharity.UniCharity.dto.request.IntrospectRequest;
import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.authentication.AuthenticationResponse;
import com.UniCharity.UniCharity.dto.response.authentication.IntrospectResponse;
import com.UniCharity.UniCharity.dto.response.authentication.RegisterResponse;
import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import com.UniCharity.UniCharity.services.iservices.IAuthenticationService;
import com.cloudinary.Api;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthencationController {
    IAuthenticationService service;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        return ApiResponse.<AuthenticationResponse>builder().result(service.authenticate(request, response)).build();
    }

    @PostMapping("/register")
    ApiResponse<RegisterResponse> register(@RequestBody UserCreateRequest request, HttpServletResponse response) {
        return ApiResponse.<RegisterResponse>builder().result(service.register(request, response)).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder().result(service.introspect(request)).build();
    }
}
