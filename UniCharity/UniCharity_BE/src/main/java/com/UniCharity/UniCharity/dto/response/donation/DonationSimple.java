package com.UniCharity.UniCharity.dto.response.donation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonationSimple {
    Integer id;
    Long amount;
    String paymentMethod;
    LocalDateTime donationDate;
}
