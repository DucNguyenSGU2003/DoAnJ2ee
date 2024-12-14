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
public class CampaignRequest {
    @NotBlank(message = "CAMPAIGN_TITLE_IS_REQUIRED")
    @Size(max = 100, message = "CAMPAIGN_TITLE_TOO_LONG")
    String title;

    @NotBlank(message = "DESCRIPTION_IS_REQUIRED")
    String description;

    @Positive(message = "TARGET_AMOUNT_MUST_BE_POSITIVE")
    long targetAmount;

    @PastOrPresent(message = "CREATED_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate createdAt = LocalDate.now();

    @Positive(message = "CREATOR_ID_MUST_BE_POSITIVE")
    int createdBy;
}
