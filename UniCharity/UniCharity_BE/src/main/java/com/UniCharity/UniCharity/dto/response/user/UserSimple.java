package com.UniCharity.UniCharity.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSimple {
    Integer id;
    String name;
    String email;
    String phone;
    String password;
    String role;
}
