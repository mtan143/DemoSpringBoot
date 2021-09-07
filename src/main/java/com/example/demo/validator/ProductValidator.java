package com.example.demo.validator;

import com.example.demo.BusinessException;
import org.springframework.data.domain.Sort;

public class ProductValidator {

    public static void validate (int page, int limit, String sort) {
        validatePage(page);
        validateLimit(limit);
        validateSort(sort);
    }
    private static void validatePage (int page) {
        if (page < 0) {
            throw  new BusinessException("Page data is invalid");
        }
    }

    private static void validateLimit (int limit) {
        if (limit < 0) {
            throw  new BusinessException("Limit data is invalid");
        }
    }

    private static void validateSort (String sort) {
        if (sort.isEmpty()) {
            throw  new BusinessException("Sort is invalid");
        }
    }

}
