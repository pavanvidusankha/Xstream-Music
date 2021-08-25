package com.example.xstream.config;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//@io.swagger.v3.oas.annotations.security.SecurityScheme(type = null)
//@Configuration
//public class SwaggerConfig {
//
//    private final String moduleName;
//    private final String apiVersion;
//
//    public SwaggerConfig(
//            @Value("${module-name}") String moduleName,
//            @Value("${api-version}") String apiVersion) {
//        this.moduleName = moduleName;
//        this.apiVersion = apiVersion;
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components()
//                                .addSecuritySchemes(securitySchemeName,
//                                        new SecurityScheme()
//                                                .name(securitySchemeName)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")
//                                )
//                )
//                .info(new Info().title(apiTitle).version(apiVersion));
//    }
//}

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

}

