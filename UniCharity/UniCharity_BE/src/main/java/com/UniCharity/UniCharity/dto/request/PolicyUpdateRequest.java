package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicyUpdateRequest {
    @NotBlank(message = "POLICY_DESCRIPTION_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "POLICY_DESCRIPTION_TOO_LONG")
    String policyDescription;

    @NotBlank(message = "ELIGIBILITY_CRITERIA_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "ELIGIBILITY_CRITERIA_TOO_LONG")
    String eligibilityCriteria;

    @NotBlank(message = "APPROVAL_REQUEST_CANNOT_BE_BLANK")
    @Pattern(regexp = "approved|pending", message = "INVALID_APPROVAL_REQUEST")
    String approvalRequired;

    @PastOrPresent(message = "UPDATE_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate updatedAt = LocalDate.now();
}
