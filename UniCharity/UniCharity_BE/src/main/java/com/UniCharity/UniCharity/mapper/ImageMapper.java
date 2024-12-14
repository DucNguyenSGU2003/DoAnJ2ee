package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.ImageCreateRequest;
import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import com.UniCharity.UniCharity.dto.response.image.ImageSimple;
import com.UniCharity.UniCharity.entities.Image;

public class ImageMapper {
    public static Image toImage(ImageCreateRequest request) {
        if (request == null) {
            return null;
        }

        Image image = new Image();

        image.setImageType(request.getImageType());

        return image;
    }

    public static ImageResponse toImageResponse(Image image) {
        if (image == null) {
            return null;
        }

        ImageResponse.ImageResponseBuilder imageResponse = ImageResponse.builder();

        imageResponse.id(image.getId());
        imageResponse.imagePath(image.getImagePath());
        imageResponse.imageType(image.getImageType());
        imageResponse.campaign(CampaignMapper.toCampaignSimple(image.getCampaign()));

        return imageResponse.build();
    }

    public static ImageSimple toImageSimple(Image image) {
        if (image == null) {
            return null;
        }

        ImageSimple.ImageSimpleBuilder imageSimple = ImageSimple.builder();

        imageSimple.id(image.getId());
        imageSimple.imagePath(image.getImagePath());
        imageSimple.imageType(image.getImageType());

        return imageSimple.build();
    }
}
