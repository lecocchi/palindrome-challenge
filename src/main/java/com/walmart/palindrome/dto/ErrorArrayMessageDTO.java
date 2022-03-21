package com.walmart.palindrome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value = "Errors")
public class ErrorArrayMessageDTO {

    @JsonProperty(value = "errors", required = true)
    private List<String> errors;

    public ErrorArrayMessageDTO(List<String> errors) {
        this.errors = errors;
    }
}
