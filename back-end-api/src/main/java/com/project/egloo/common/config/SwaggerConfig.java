package com.project.egloo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private final String authorization = "Authorization";

    @Bean
    public OpenAPI pointBerryOpenAPI() {

        return new OpenAPI()
            .info(
                new Info()
                    .title("Egloo API Document")
                    .description("[Egloo] REST API")
                    .contact(
                        new Contact()
                            .name("[Egloo Swagger]")
                            .url("https://github.com/penguinsAtEgloo/E-Gloo")
                            .email("helloegloo@gmail.com")
                    )
                    .version("v0.1.0"))
            .components(
                new Components()
                    .addSecuritySchemes(authorization, bearerSecuritySchema())
            )
            .security(Collections.singletonList(new SecurityRequirement().addList(authorization)));
    }

    private SecurityScheme bearerSecuritySchema() {
        return new SecurityScheme()
            .name(authorization)
            .description("JWT Token")
            .scheme("bearer")
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT");
    }
}
