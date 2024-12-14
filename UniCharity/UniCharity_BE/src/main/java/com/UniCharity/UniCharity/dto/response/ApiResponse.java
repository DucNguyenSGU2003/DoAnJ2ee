package com.UniCharity.UniCharity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // Các giá trị null không được trả về
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;
    String message;
    T result;
    String errorDetail;
    // Trường bổ sung để chứa thông tin chi tiết lỗi
    private String redirectUrl;  // Thêm trường này

    // Các getter và setter
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
