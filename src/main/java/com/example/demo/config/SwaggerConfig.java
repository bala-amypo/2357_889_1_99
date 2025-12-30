package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {


        SecurityScheme bearerAuth = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                
                .info(new Info()
                        .title("Asset Lifecycle Management API")
                        .description("API for managing assets, vendors, depreciation rules, lifecycle events, and disposals")
                        .version("1.0"))

               
                .servers(List.of(
                        new Server().url("https://9365.pro604cr.amypo.ai/")
                ))

                
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerAuth));

                
    }
}
