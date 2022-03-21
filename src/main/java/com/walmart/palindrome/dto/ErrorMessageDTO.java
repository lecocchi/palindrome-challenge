package com.walmart.palindrome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Error")
public class ErrorMessageDTO {
    @JsonProperty(value = "message", required = true)
    private String message;

    public ErrorMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
