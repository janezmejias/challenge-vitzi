package com.lean.vitzi.infrastructure.adapters.out.singleton;

import com.lean.vitzi.application.api.ConstantsVitzi;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.lean.vitzi.application.api.ConstantsVitzi.*;

/**
 * @author janez@vitzi.com
 * @version 2022-10-13
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class APIProperties {

    private Map<String, String> analyticsPropertiesMap;

    @PostConstruct
    public void init() {
        analyticsPropertiesMap = new HashMap<>();

        analyticsPropertiesMap.put(API_DOMAIN, "");
        analyticsPropertiesMap.put(FIELD_API_KEY, "");
        analyticsPropertiesMap.put(USER_ID, "");
    }

    @SneakyThrows
    @ReadOnlyProperty
    public String getPropertyBy(String key) {
        if (analyticsPropertiesMap.containsKey(key)) {
            return analyticsPropertiesMap.get(key);
        }
        throw new Exception(ConstantsVitzi.PROPERTY_NOT_FOUND_MESSAGE + key);
    }
}
