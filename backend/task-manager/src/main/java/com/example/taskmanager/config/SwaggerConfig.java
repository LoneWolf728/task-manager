package com.example.taskmanager.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * Provides API documentation with JWT authentication support.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates and configures the OpenAPI documentation.
     * 
     * @return An OpenAPI instance configured with JWT authentication
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Add global security requirement for JWT authentication
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        // Define the JWT security scheme
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP) // HTTP authentication type
                                        .scheme("bearer")               // Bearer authentication scheme
                                        .bearerFormat("JWT")));         // JWT token format
    }
}