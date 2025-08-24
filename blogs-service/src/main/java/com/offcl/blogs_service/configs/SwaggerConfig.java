package com.offcl.blogs_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blogs Service API")
                        .description("API documentation for Blogs Service")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gowtham K")
                                .email("gowthamkoffcl@gmail.com")));
    }
}
