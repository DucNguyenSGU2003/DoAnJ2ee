package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignCreateRequest {
    @NotBlank(message = "CAMPAIGN_TITLE_IS_REQUIRED")
    String title;

    @NotBlank(message = "DESCRIPTION_IS_REQUIRED")
    String description;

    @Positive(message = "TARGET_AMOUNT_MUST_BE_POSITIVE")
    long targetAmount;

    @Min(value = 0, message = "CURRENT_AMOUNT_MUST_BE_NON_NEGATIVE")
    long currentAmount = 0;

    @PastOrPresent(message = "CREATED_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate createdAt = LocalDate.now();

    @FutureOrPresent(message = "START_DATE_MUST_BE_FUTURE_OR_PRESENT")
    LocalDate startDate;

    @Future(message = "END_DATE_MUST_BE_IN_FUTURE")
    LocalDate endDate;

    @Positive(message = "CREATOR_ID_MUST_BE_POSITIVE")
    int createdBy;

    @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
    @Pattern(regexp = "Active|Pending|Completed|Cancelled", message = "INVALID_STATUS_VALUE")
    String status;

    List<PolicyCreateRequest> policies;
}
