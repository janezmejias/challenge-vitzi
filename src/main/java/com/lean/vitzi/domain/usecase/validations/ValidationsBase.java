package com.lean.vitzi.domain.usecase.validations;

import com.lean.vitzi.application.request.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationsBase {

    /**
     * Nested abstract value types should be declared static if declared as inner classes
     * (interfaces and annotations are implicitly static if nested).
     * <p>
     * You are not limited to using classes that you control.
     * You can generate immutable implementation classes from the abstract types in other packages.
     * <p>
     * The @RequestBase can be used on types and packages.
     * This is most useful when you want to generate implementations to use with
     */
    public void validate(RequestContext requestContext) {
    }

}
