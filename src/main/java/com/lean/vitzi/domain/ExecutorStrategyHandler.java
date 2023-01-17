package com.lean.vitzi.domain;

import com.lean.vitzi.application.request.RequestContext;
import com.lean.vitzi.application.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Service
@RequiredArgsConstructor
public class ExecutorStrategyHandler {

    private final ApplicationContext applicationContext;

    /**
     * The original object, called context, holds a reference to a strategy object.
     *
     * The context delegates executing the behavior to the linked strategy object.
     *
     * In order to change the way the context performs its work, other objects may replace the currently
     * linked strategy object with another one.
     * @param requestContext
     * @return
     */
    public ResponseBase with(RequestContext requestContext) {
        ExecutorStrategy executorStrategy = (ExecutorStrategy) applicationContext.getBean(requestContext.getFlow());
        return executorStrategy.of(requestContext);
    }
}
