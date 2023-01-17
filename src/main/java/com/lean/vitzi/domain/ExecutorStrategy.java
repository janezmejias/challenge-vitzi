package com.lean.vitzi.domain;

import com.lean.vitzi.application.request.RequestContext;
import com.lean.vitzi.application.response.ResponseBase;
import org.springframework.stereotype.Component;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Component
public interface ExecutorStrategy {
    /**
     * @param requestContext
     * @return
     */
    ResponseBase of(RequestContext requestContext);
}
