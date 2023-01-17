package com.lean.vitzi.application.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Data
public class ResponseBase<T> implements Serializable {

    private Boolean success;
    private String message;
    private transient T body;

    public ResponseBase<T> onSuccess(T body) {
        this.success = Boolean.TRUE;
        this.message = "success";
        this.body = body;
        return this;
    }

    public ResponseBase<T> onFailure(String errorMessage) {
        this.success = Boolean.FALSE;
        this.message = errorMessage;
        return this;
    }

}

