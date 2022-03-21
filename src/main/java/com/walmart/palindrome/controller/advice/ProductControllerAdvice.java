package com.walmart.palindrome.controller.advice;

import com.walmart.palindrome.dto.ErrorArrayMessageDTO;
import com.walmart.palindrome.dto.ErrorMessageDTO;
import com.walmart.palindrome.exception.ProductException;
import com.walmart.palindrome.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerAdvice.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> productNotFoundException(ProductNotFoundException e) {
        logger.error(e.getMessage());
        return new ResponseEntity(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.class)

    public ResponseEntity<ErrorMessageDTO> productException(ProductException e) {
        logger.error(e.getMessage());
        return new ResponseEntity(new ErrorMessageDTO("It is not possible to process the request"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorArrayMessageDTO> handleConstraintViolationException(ConstraintViolationException e) {

        List<String> errors = e.getConstraintViolations().stream().map(c -> c.getMessageTemplate()).collect(Collectors.toList());

        logger.error(errors.toString());

        return new ResponseEntity(new ErrorArrayMessageDTO(errors), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "The field " + ex.getParameterName() + " is required";

        List<String> errors = new ArrayList<>();
        errors.add(errorMessage);

        logger.error(errors.toString());

        return new ResponseEntity(new ErrorArrayMessageDTO(errors), HttpStatus.BAD_REQUEST);
    }
}
