package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FundAllocationUpdateRequest {
    @NotBlank(message = "ALLOCATION_TYPE_CANNOT_BE_BLANK")
    @Size(max = 100, message = "ALLOCATION_TYPE_TOO_LONG")
    String category;

    @NotNull(message = "AMOUNT_CANNOT_BE_NULL")
    @PositiveOrZero(message = "AMOUNT_MUST_BE_POSITIVE_OR_ZERO")
    Long amount;

    @NotBlank(message = "F_A_DESCRIPTION_CANNOT_BE_BLANK")
    @Size(max = 1000, message = "F_A_DESCRIPTION_TOO_LONG")
    String description;
}
