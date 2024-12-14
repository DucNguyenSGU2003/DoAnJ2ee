package com.UniCharity.UniCharity.utils;

import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtils {
    public static <T> void sortList(List<T> list, String sortField, String sortDirection) {
        if (list == null || list.isEmpty()) {
            return; // Danh sách trống, không cần sắp xếp
        }

        // Lấy lớp của phần tử
        Class<?> clazz = list.get(0).getClass();

        try {
            // Lấy field tương ứng
            Field field = clazz.getDeclaredField(sortField);
            field.setAccessible(true); // Cho phép truy cập vào private field

            // Tạo comparator dựa trên giá trị của field
            Comparator<T> comparator = (o1, o2) -> {
                try {
                    Object value1 = field.get(o1);
                    Object value2 = field.get(o2);

                    if (value1 == null && value2 == null) return 0;
                    if (value1 == null) return -1;
                    if (value2 == null) return 1;

                    if (value1 instanceof Comparable) {
                        return ((Comparable) value1).compareTo(value2);
                    } else {
                        throw new IllegalArgumentException("Field is not comparable");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot access field: " + sortField, e);
                }
            };

            // Áp dụng thứ tự giảm dần nếu cần
            if ("desc".equalsIgnoreCase(sortDirection)) {
                comparator = comparator.reversed();
            }

            // Sắp xếp danh sách
            Collections.sort(list, comparator);

        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid sort field: " + sortField, e);
        }
    }
}
