package com.UniCharity.UniCharity.dto.response.policyViolation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicyViolationSimple {
    Integer id;
    String violationDescription;
    LocalDate violationDate;
    String status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
