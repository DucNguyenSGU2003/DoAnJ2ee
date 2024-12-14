package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.request.AuthenticationRequest;
import com.UniCharity.UniCharity.dto.request.IntrospectRequest;
import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.response.authentication.AuthenticationResponse;
import com.UniCharity.UniCharity.dto.response.authentication.IntrospectResponse;
import com.UniCharity.UniCharity.dto.response.authentication.RegisterResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response);
    RegisterResponse register(UserCreateRequest request, HttpServletResponse response);
}
