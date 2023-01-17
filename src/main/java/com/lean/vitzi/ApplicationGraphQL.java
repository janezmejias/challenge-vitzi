package com.lean.vitzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lean.vitzi.*")
@EntityScan(basePackages = {"com.lean.vitzi.*"})
@EnableJpaRepositories(basePackages = {"com.lean.vitzi.*"})
public class ApplicationGraphQL {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGraphQL.class, args);
    }

}
