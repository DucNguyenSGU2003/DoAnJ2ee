package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolicyViolationUpdateRequest {
    @NotBlank(message = "VIOLATION_DESCRIPTION_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "VIOLATION_DESCRIPTION_TOO_LONG")
    String violationDescription;

    @NotNull(message = "VIOLATION_DESCRIPTION_CANNOT_BE_BLANK")
    @PastOrPresent(message = "VIOLATION_DESCRIPTION_TOO_LONG")
    LocalDate violationDate;

    @NotBlank(message = "VIOLATION_DESCRIPTION_TOO_LONG")
    @Pattern(regexp = "OPEN|CLOSED", message = "VIOLATION_STATUS_CANNOT_BE_BLANK")
    String status;

    @PastOrPresent(message = "UPDATE_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate updatedAt = LocalDate.now();
}
