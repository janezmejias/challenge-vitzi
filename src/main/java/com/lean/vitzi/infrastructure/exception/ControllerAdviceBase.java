package com.lean.vitzi.infrastructure.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lean.vitzi.application.response.ResponseBase;
import org.modelmapper.ValidationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.AttributeNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceBase extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseBase> generalException(Exception exception, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseBase> constraintViolationException(ConstraintViolationException ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ResponseBase> responseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.add(err.getField() + ": " + err.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(err -> errors.add(err.getObjectName() + ": " + err.getDefaultMessage()));
        StringBuilder builder = new StringBuilder(ex.getMessage());
        errors.forEach(s -> builder.append(" ").append(s));
        ResponseBase errorResponse = buildErrorMessage(builder.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    public ResponseEntity<ResponseBase> undeclaredThrowableException(UndeclaredThrowableException ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ResponseBase> handleConverterErrors(JsonMappingException ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler({CannotCreateTransactionException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleDataAccessResourceException(CannotCreateTransactionException ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AttributeNotFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleAttributeNotFoundException(Exception ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({QueryTimeoutException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleQueryTimeoutException(Exception ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({JsonProcessingException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleJsonProcessingException(Exception ex, HttpServletRequest request) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public ResponseEntity<ResponseBase> handleBadRequestException(HttpClientErrorException ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler({HttpClientErrorException.TooManyRequests.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleTooManyRequestException(HttpClientErrorException ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HttpClientErrorException.Conflict.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleConflictException(HttpClientErrorException.Conflict ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> handleInterServerErrorException(HttpServerErrorException ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HttpServerErrorException.ServiceUnavailable.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase> unavailableException(HttpServerErrorException.ServiceUnavailable ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ResponseBase> handleCatchErrors(Exception ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseBase> handlevalidationException(ValidationException ex) {
        ResponseBase errorResponse = buildErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseBase buildErrorMessage(String message) {
        return new ResponseBase().onFailure(message);
    }
}

