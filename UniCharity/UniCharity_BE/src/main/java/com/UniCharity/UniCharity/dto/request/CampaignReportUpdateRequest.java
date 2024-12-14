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
public class CampaignReportUpdateRequest {
    @NotNull(message = "DONATION_AMOUNT_CANNOT_BE_NULL")
    @PositiveOrZero(message = "DONATION_AMOUNT_MUST_BE_POSITIVE_OR_ZERO")
    Long totalDonations;

    @NotNull(message = "NUMBER_OF_RECIPIENTS_CANNOT_BE_NULL")
    @PositiveOrZero(message = "NUMBER_OF_RECIPIENTS_MUST_BE_POSITIVE_OR_ZERO")
    Long totalRecipients;

    @NotBlank(message = "SUMMARY_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "SUMMARY_TOO_LONG")
    String resultsSummary;

    @NotBlank(message = "LESSONS_LEARNED_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "LESSONS_LEARNED_TOO_LONG")
    String lessonsLearned;

    @FutureOrPresent(message = "REPORT_DATE_MUST_BE_FUTURE_OR_PRESENT")
    LocalDate reportDate;

    @PastOrPresent(message = "UPDATE_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate updatedAt = LocalDate.now();
}
