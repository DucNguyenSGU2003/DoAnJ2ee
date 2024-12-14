package com.UniCharity.UniCharity.utils;

import com.UniCharity.UniCharity.entities.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;


@Slf4j
public class JwtUtils {
    protected static String JWT_SECRET = "K1YJD9Mt3V3wklqFpuYQXZhKSMdh6UZyLBb/nvXKBk1Lw3yXtRYTcpLqwOa8ACsc";
    protected static long JWT_EXPIRATION = 259200000;

    @Autowired
    public void setEnvironment(Environment environment) {
        JWT_SECRET = environment.getProperty("jwt.signerKey");
        JWT_EXPIRATION = Long.parseLong(environment.getProperty("jwt.expirationTime"));
    }

    public static String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .claim("id", user.getId())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(JWT_SECRET.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public static void addTokenToCookie(HttpServletResponse response, String token) {
        // Lưu token vào cookie với các thuộc tính bảo mật
        Cookie cookie = new Cookie("auth_token", token);
        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
        response.addCookie(cookie);
    }

    // Lấy thông tin user từ jwt
    public static String getUserEmailFromJWT(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid or expired JWT token", e);
            return null;
        }
    }

    public static String getUserRoleFromJWT(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            return claims.get("role", String.class);
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid or expired JWT token", e);
            return null;
        }
    }

    public static boolean validateToken(String authToken) throws Exception {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
