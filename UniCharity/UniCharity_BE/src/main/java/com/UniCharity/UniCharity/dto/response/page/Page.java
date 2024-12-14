package com.UniCharity.UniCharity.dto.response.page;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {
    long totalItem; // Tổng số phần tử
    int currentPage; // Trang hiện tại
    int totalPages; // Tổng số trang
    int pageSize; // Số phần tử mỗi trang
}
