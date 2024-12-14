package com.UniCharity.UniCharity.dto.response.violationAction;

import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViolationActionResponse {
    Integer id;
    String actionDescription;
    LocalDate actionDate;
    String status;
    LocalDate createdAt;

    PolicyViolationSimple violation;
}
