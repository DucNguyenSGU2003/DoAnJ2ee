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
public class CampaignReportCreateRequest {
    @Positive(message = "CAMPAIGN_ID_MUST_BE_POSITIVE")
    int campaign;

    @NotNull(message = "DONATION_AMOUNT_CANNOT_BE_NULL")
    @PositiveOrZero(message = "DONATION_AMOUNT_MUST_BE_POSITIVE_OR_ZERO")
    Long totalDonations;

    @NotNull(message = "NUMBER_OF_RECIPIENTS_CANNOT_BE_NULL")
    @PositiveOrZero(message = "NUMBER_OF_RECIPIENTS_MUST_BE_POSITIVE_OR_ZERO")
    Long totalRecipients;

    @NotBlank(message = "SUMMARY_CANNOT_BE_BLANK")
    String resultsSummary;

    @NotBlank(message = "LESSONS_LEARNED_CANNOT_BE_BLANK")
    String lessonsLearned;

    LocalDate createdAt = LocalDate.now();

    LocalDate reportDate;

    LocalDate updatedAt;
}
