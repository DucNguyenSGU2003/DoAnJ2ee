package com.UniCharity.UniCharity.dto.response.violationAction;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViolationActionSimple {
    Integer id;
    String actionDescription;
    LocalDate actionDate;
    String status;
    LocalDate createdAt;
}
