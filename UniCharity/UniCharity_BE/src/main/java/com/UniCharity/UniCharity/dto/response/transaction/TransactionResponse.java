package com.UniCharity.UniCharity.dto.response.transaction;

import com.UniCharity.UniCharity.dto.response.donation.DonationSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    Integer id;
    String transactionCode;
    String paymentGateway;
    LocalDateTime transactionDate;
    String transactionStatus;
    Long amount;
    String responseCode;
    String transactionDescription;

    DonationSimple donation;
}
