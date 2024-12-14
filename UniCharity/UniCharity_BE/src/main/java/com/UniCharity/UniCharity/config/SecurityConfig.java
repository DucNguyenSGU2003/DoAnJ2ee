package com.UniCharity.UniCharity.config;

import com.UniCharity.UniCharity.config.security.JwtAuthenticationFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINT = {
            "/auth/login",
            "/auth/register",
            "/auth/introspect",
            "/vnpay/create_payment",
            "/vnpay/payment-return",
            "/campaigns/get-by-status/*",
            "/campaigns/get-by-id/*",
            "/vnpay/*",
            "/donation/get-by-user-id/*",


    };
    @Value("${jwt.signerKey}")
    private String signerKey;

    //  JWT
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

//     bật jwt
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults()) // Kích hoạt CORS
                .csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll() // Cho phép truy cập công khai các endpoint này
                        .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các endpoint còn lại
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Thêm bộ lọc JWT trước UsernamePasswordAuthenticationFilter
        return httpSecurity.build();
    }

//    // tắt jwt
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF
//                .cors(Customizer.withDefaults()) // Kích hoạt CORS (nếu cần)
//                .authorizeHttpRequests(request -> request
//                        .anyRequest().permitAll() // Cho phép tất cả các request mà không cần xác thực
//                )
//                .sessionManagement(session -> session.disable()) // Vô hiệu hóa quản lý phiên
//                .securityContext(context -> context.disable()); // Vô hiệu hóa SecurityContext
//
//        return httpSecurity.build();
//    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

