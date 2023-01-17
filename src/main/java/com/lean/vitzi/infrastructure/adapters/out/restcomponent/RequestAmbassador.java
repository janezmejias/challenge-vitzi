package com.lean.vitzi.infrastructure.adapters.out.restcomponent;

import com.lean.vitzi.application.api.ConstantsVitzi;
import com.lean.vitzi.application.request.RequestBase;
import com.lean.vitzi.application.request.RequestContext;
import com.lean.vitzi.infrastructure.adapters.out.singleton.APIProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import static com.lean.vitzi.application.api.ConstantsVitzi.API_DOMAIN;
import static com.lean.vitzi.application.api.ConstantsVitzi.FIELD_API_KEY;
import static com.lean.vitzi.infrastructure.exception.HttpExceptionHelper.createException;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Component
@EnableRetry
@Slf4j
@RequiredArgsConstructor
public class RequestAmbassador {

    private final APIProperties apiProperties;
    private final ParamsAdvice paramsAdvice;

    @Retryable(
            maxAttemptsExpression = "${api.qrvey.retry-number}",
            backoff = @Backoff(delayExpression = "${api.qrvey.delay}"),
            value = {
                    Exception.class,
                    HttpClientErrorException.class,
                    HttpServerErrorException.class,
                    HttpStatusCodeException.class},
            listeners = {"customRetryListener"}, recover = "recover")
    public ResponseEntity<?> out(RequestContext requestContext) {
        paramsAdvice.exchange(requestContext);
        String apiKey = apiProperties.getPropertyBy(FIELD_API_KEY);
        String url = apiProperties.getPropertyBy(API_DOMAIN);

        RequestBase requestBase = requestContext.getRequestBase();
        HttpHeaders headers = new HttpHeaders();
        headers.add(ConstantsVitzi.API_KEY, apiKey);
        headers.add(ConstantsVitzi.CONTENT_TYPE, ConstantsVitzi.APPLICATION_JSON);

        return ResponseEntity.ok(requestBase);
    }

    @SneakyThrows
    @Recover
    private ResponseEntity<String> recover(Exception ex, RequestContext requestContext) {
        log.error(ConstantsVitzi.GENERIC_ERROR_MESSAGE, ex.getMessage());
        throw createException(ex);
    }
}
