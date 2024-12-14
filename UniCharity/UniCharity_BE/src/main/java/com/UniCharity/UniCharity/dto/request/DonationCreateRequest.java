package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonationCreateRequest {
    int campaign;
    Long amount;
    LocalDateTime donationDate = LocalDateTime.now();
    @Email(message = "Email không hợp lệ")
    String email;
    String name;
}
