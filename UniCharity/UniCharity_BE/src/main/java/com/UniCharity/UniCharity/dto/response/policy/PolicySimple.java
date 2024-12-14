package com.UniCharity.UniCharity.dto.response.policy;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicySimple {
    Integer id;
    String policyDescription;
    String eligibilityCriteria;
    String approvalRequired;
    LocalDate createdAt;
    LocalDate updatedAt;
}
