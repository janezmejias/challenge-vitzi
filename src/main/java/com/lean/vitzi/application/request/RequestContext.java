package com.lean.vitzi.application.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Builder
@Data
public class RequestContext {

    private RequestBase requestBase;
    private String flow;
}
