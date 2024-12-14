package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @Email(message = "INVALID_EMAIL")
    @NotBlank(message = "EMAIL_IS_REQUIRED")
    String email;

    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    @Size(min = 8, message = "PASSWORD_TOO_SHORT")
    @Pattern(regexp = ".*[A-Z].*", message = "PASSWORD_MUST_CONTAIN_UPPERCASE")
    @Pattern(regexp = ".*[a-z].*", message = "PASSWORD_MUST_CONTAIN_LOWERCASE")
    @Pattern(regexp = ".*\\d.*", message = "PASSWORD_MUST_CONTAIN_NUMBER")
    @Pattern(regexp = ".*[@$!%*?&].*", message = "PASSWORD_MUST_CONTAIN_SPECIAL_CHARACTER")
    String password;
}
