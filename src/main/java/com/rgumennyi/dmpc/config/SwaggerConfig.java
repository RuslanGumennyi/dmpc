package com.rgumennyi.dmpc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    private GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("com.rgumennyi.dmpc")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI description() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dunder Mifflin Paper Company")
                        .description("Dunder Mifflin Paper Company API")
                        .version("1.0.0")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Ruslan Gumennyi")
                        .url("ruslan.gumennyi@example.com")
                );
    }
}