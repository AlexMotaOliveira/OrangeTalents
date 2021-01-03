package com.orangestalents;

import com.orangestalents.dto.request.UserDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class UserApplication implements RepositoryRestConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/api/v1/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT","DELETE");
    }

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vRel) {
        Validator validator = validator();
        vRel.addValidator("beforeCreate", validator);
        vRel.addValidator("beforeSave", validator);
    }
}