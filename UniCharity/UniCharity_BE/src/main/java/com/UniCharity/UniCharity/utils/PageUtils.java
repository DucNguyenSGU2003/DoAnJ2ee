package com.UniCharity.UniCharity.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtils {
    public static  <T> Page<T> paginateList(List<T> list, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<T> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }
}
