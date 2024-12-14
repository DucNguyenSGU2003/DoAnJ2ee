package com.UniCharity.UniCharity.dto.response.authentication;

import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    UserResponse userResponse;
    String token;
}
