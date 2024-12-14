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
public class ViolationActionUpdateRequest {
    @NotBlank(message = "ACTION_DESCRIPTION_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "ACTION_DESCRIPTION_TOO_LONG")
    String actionDescription;

    @NotNull(message = "ACTION_DATE_CANNOT_BE_NULL")
    @PastOrPresent(message = "ACTION_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate actionDate;

    @NotBlank(message = "ACTION_STATUS_CANNOT_BE_BLANK")
    @Pattern(regexp = "PENDING|COMPLETED", message = "INVALID_STATUS")
    String status;
}
