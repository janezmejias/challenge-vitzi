package com.lean.vitzi.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
public class HttpExceptionHelper {

    private HttpExceptionHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static RuntimeException createException(Exception exception) {
        if (exception instanceof HttpServerErrorException) {
            HttpServerErrorException e = (HttpServerErrorException) exception;
            return HttpExceptionHelper.createServerErrorException(e);
        } else if (exception instanceof HttpClientErrorException) {
            HttpClientErrorException e = (HttpClientErrorException) exception;
            return HttpExceptionHelper.createClientErrorException(e);
        } else if (exception instanceof ResourceAccessException) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "SocketTimeoutException ".concat(exception.getMessage()));
        } else {
            return new IllegalStateException(String.format("%s, %s", exception.getMessage(), exception.getMessage()));
        }
    }

    public static HttpServerErrorException createServerErrorException(HttpServerErrorException httpException) {
        String responseMessage = httpException.getResponseBodyAsString().replace("\n", " ");
        return HttpServerErrorException.create(responseMessage, httpException.getStatusCode(),
                httpException.getStatusText(),
                Optional.ofNullable(httpException.getResponseHeaders()).orElseGet(HttpHeaders::new),
                httpException.getResponseBodyAsByteArray(),
                StandardCharsets.UTF_8);
    }

    public static HttpClientErrorException createClientErrorException(HttpClientErrorException httpException) {
        String responseMessage = httpException.getResponseBodyAsString().replace("\n", " ");
        return HttpClientErrorException.create(responseMessage, httpException.getStatusCode(),
                httpException.getStatusText(),
                Optional.ofNullable(httpException.getResponseHeaders()).orElseGet(HttpHeaders::new),
                httpException.getResponseBodyAsByteArray(),
                StandardCharsets.UTF_8);
    }

}
