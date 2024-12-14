package com.UniCharity.UniCharity.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionUpdateRequest {
    String transactionCode;
    String paymentGateway;
    LocalDateTime transactionDate;
    String transactionStatus;
    Long amount;
    String responseCode;
    String transactionDescription;
}
