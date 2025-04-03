package com.freelance.kiosk_backend.shared.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Kiosk Service", version = "1.0", description = "Patient medical application"))
public class SwaggerConfig {
}
