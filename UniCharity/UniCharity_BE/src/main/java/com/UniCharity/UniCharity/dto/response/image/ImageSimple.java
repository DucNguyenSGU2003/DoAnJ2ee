package com.UniCharity.UniCharity.dto.response.image;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageSimple {
    Integer id;
    String imagePath;
    String imageType;
}
