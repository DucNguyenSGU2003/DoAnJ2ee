package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 3, message = "")
    String name;
    @Email(message = "Email không hợp lệ")
    String email;
    String phone;
    String password;
    String role;
}
