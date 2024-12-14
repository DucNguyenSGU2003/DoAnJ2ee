package com.UniCharity.UniCharity.dto.response.image;

import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageResponse {
    Integer id;
    String imagePath;
    String imageType;

    CampaignSimple campaign;
}
