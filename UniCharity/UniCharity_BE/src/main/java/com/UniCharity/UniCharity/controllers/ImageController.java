package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import com.UniCharity.UniCharity.constant.ImageType;
import com.UniCharity.UniCharity.services.iservices.IImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageController {
    IImageService imageService;

    @PostMapping("/upload-image/{campaignId}")
    ApiResponse<ImageResponse> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(value = "type", defaultValue = ImageType.BANNER) String imageType, @PathVariable("campaignId") int campaignId) throws IOException {
        return ApiResponse.<ImageResponse>builder().result(imageService.uploadImage(file, imageType, campaignId)).build();
    }

    @PostMapping("/upload-images/{campaignId}")
    ApiResponse<List<ImageResponse>> uploadImages(@RequestParam("files") List<MultipartFile> files, @RequestParam(value = "type", defaultValue = ImageType.BANNER) String imageType, @PathVariable("campaignId") int campaignId) throws IOException {
        return ApiResponse.<List<ImageResponse>>builder().result(imageService.uploadImages(files, imageType, campaignId)).build();
    }

    @GetMapping("/dowload-by-id/{imageId}")
    ApiResponse<ImageResponse> getImageById(@PathVariable("imageId") int imageId) {
        return ApiResponse.<ImageResponse>builder().result(imageService.dowloadImage(imageId)).build();
    }

    @GetMapping("/dowload-by-campaignId/{campaignId}")
    ApiResponse<List<ImageResponse>> getImageByCampaignId(@PathVariable("campaignId") int campaignId) {
        return ApiResponse.<List<ImageResponse>>builder().result(imageService.dowloadImageByCampaign(campaignId)).build();
    }

    @DeleteMapping("/delete/{imageId}")
    ApiResponse<List<ImageResponse>> removeImage(@PathVariable("imageId") int imageId) {
        return ApiResponse.<List<ImageResponse>>builder().result(imageService.removeImage(imageId)).build();
    }
}
