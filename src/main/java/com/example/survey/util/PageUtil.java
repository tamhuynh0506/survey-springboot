package com.example.survey.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class PageUtil {

    public static Pageable buildPageable(int page, int size, String sortBy, String direction) {
        if (!StringUtils.hasText(sortBy)) {
            sortBy = "createdAt";
        }

        if (!StringUtils.hasText(direction)) {
            direction = "desc";
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }
}
