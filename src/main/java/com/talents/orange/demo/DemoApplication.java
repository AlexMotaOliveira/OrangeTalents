package com.talents.orange.demo;

import com.talents.orange.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DemoApplication implements RepositoryRestConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("Orange Talants api in action");

    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(User.class);
        config.getCorsRegistry()
                .addMapping("/**")  // #TODO Definir URL's/ IP's de acesso
                .allowedOrigins("*")  // #TODO Definir HOST's de acesso
                .allowedMethods("*");

        logger.info("Repository Cors... ok");

    }

    @Bean
    public Validator validator (){
        return  new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        Validator validator = validator();
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave",validator);

        logger.info("Configure validator... ok");
    }
}
