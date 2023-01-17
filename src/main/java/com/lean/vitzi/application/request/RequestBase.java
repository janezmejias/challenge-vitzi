package com.lean.vitzi.application.request;

import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Data
public class RequestBase {

    @NotNull(message = "patch is required")
    @NotEmpty(message = "patch don't can be empty")
    private String patch;

    private String body;
    private HttpMethod httpMethod;
}
