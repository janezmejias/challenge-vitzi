package com.lean.vitzi.infrastructure.adapters.out.restcomponent;

import com.lean.vitzi.application.api.ConstantsVitzi;
import com.lean.vitzi.application.request.RequestContext;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Product;
import com.lean.vitzi.infrastructure.adapters.out.mysql.repositories.ProductRepositoryPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.lean.vitzi.application.api.ConstantsVitzi.*;

/**
 * @author janez@vitzi.com
 * @version 2022-10-13
 */
@Component
@RequiredArgsConstructor
public class ParamsAdvice {

    private final ProductRepositoryPort productRepositoryPort;

    public void exchange(RequestContext requestContext) {
        Optional<Product> commonSetting = productRepositoryPort.findByName("--");
        if (commonSetting.isPresent()) {
            Product product = commonSetting.get();

            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put(USER_ID, product.getId().toString());
            paramsMap.put(APP_ID, product.getName());
            paramsMap.put(CLIENT_ID, "1");

            requestContext.getRequestBase().setPatch(swap(requestContext.getRequestBase().getPatch(), paramsMap));
            requestContext.getRequestBase().setBody(swap(requestContext.getRequestBase().getBody(), paramsMap));
            return;
        }
        throw new RuntimeException(ConstantsVitzi.INVALID_ACCOUNT);
    }

    private String swap(@NonNull String content, Map<String, String> paramsMap) {
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (content.contains(key)) {
                content = content.replaceAll(key, value);
            }
        }
        return content;
    }

}
