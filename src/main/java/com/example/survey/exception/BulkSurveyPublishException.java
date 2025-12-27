package com.example.survey.exception;

import java.util.List;

import com.example.survey.dto.InvalidSurveyPublishItem;

public class BulkSurveyPublishException extends RuntimeException {

    private final List<InvalidSurveyPublishItem> invalidItems;

    public BulkSurveyPublishException(List<InvalidSurveyPublishItem> invalidItems) {
        super("One or more surveys cannot be published");
        this.invalidItems = invalidItems;
    }

    public List<InvalidSurveyPublishItem> getInvalidItems() {
        return invalidItems;
    }
}
