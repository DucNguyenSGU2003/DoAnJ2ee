package com.UniCharity.UniCharity.dto.response.transaction;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionSimple {
    Integer id;
    String transactionCode;
    String paymentGateway;
    LocalDateTime transactionDate;
    String transactionStatus;
    Long amount;
    String responseCode;
    String transactionDescription;
}
