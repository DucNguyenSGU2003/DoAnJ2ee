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
public class FundAllocationCreateRequest {
    @Positive(message = "REPORT_ID_MUST_BE_POSITIVE")
    Integer report;

    @NotBlank(message = "ALLOCATION_TYPE_CANNOT_BE_BLANK")
    @Size(max = 100, message = "ALLOCATION_TYPE_TOO_LONG")
    String category;

    @NotNull(message = "AMOUNT_CANNOT_BE_NULL")
    @PositiveOrZero(message = "AMOUNT_MUST_BE_POSITIVE_OR_ZERO")
    Long amount;

    @NotBlank(message = "F_A_DESCRIPTION_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "F_A_DESCRIPTION_TOO_LONG")
    String description;

    @PastOrPresent(message = "CREATED_DATE_MUST_BE_PAST_OR_PRESENT")
    LocalDate createdAt = LocalDate.now();

    Integer user;
}
