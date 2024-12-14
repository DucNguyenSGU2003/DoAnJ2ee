package com.UniCharity.UniCharity.dto.response.campaign;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignSimple {
    Integer id;
    String title;
    String description;
    long targetAmount;
    long currentAmount;
    LocalDate createdAt;
    LocalDate startDate;
    LocalDate endDate;
    String status;
}
