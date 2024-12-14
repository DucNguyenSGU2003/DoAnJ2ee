package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    public ImageResponse uploadImage(MultipartFile imageFile, String imageType, int campaignId) throws IOException;
    public List<ImageResponse> uploadImages(List<MultipartFile> imageFiles, String imageType, int campaignId) throws IOException;
    public ImageResponse dowloadImage(int imageId);
    public List<ImageResponse> dowloadImageByCampaign(int campaignId);
    public List<ImageResponse> removeImage(int imageId);
}
