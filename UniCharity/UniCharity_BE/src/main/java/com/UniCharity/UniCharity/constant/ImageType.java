package com.UniCharity.UniCharity.constant;

import lombok.Getter;

@Getter
public class ImageType {
    public static final String ILLUSTRATION = "Illustration";
    public static final String BANNER = "Banner";

    private ImageType() {
        // Ngăn việc khởi tạo lớp này
    }
}
