package com.UniCharity.UniCharity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageCreateRequest {
    @Positive(message = "CAMPAIGN_ID_MUST_BE_POSITIVE")
    int campaign;

    @NotBlank(message = "IMAGE_TYPE_CANNOT_BE_BLANK")
    @Pattern(regexp = "campaign|result", message = "INVALID_IMAGE_TYPE")
    String imageType;
}
