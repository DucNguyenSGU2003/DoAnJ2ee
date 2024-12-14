package com.UniCharity.UniCharity.dto.response.fundAllocation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FundAllocationSimple {
    Integer id;
    String category;
    Long amount;
    String description;
    LocalDate createdAt;
}
