package com.example.survey.util;

import java.util.Optional;

import com.example.survey.exception.NotFoundException;

public class FetchUtil {
    public static <T> T orThrow(Optional<T> optional, Class<T> entityClass) {
        return optional.orElseThrow(() -> new NotFoundException(
                entityClass.getSimpleName()));
    }
}
