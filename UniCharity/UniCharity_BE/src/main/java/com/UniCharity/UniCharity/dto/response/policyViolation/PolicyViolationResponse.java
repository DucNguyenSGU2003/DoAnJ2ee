package com.UniCharity.UniCharity.dto.response.policyViolation;

import com.UniCharity.UniCharity.dto.response.policy.PolicySimple;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicyViolationResponse {
    Integer id;
    String violationDescription;
    LocalDate violationDate;
    String status;
    LocalDate createdAt;
    LocalDate updatedAt;

    PolicySimple policy;

    List<ViolationActionResponse> violationActions;
}
