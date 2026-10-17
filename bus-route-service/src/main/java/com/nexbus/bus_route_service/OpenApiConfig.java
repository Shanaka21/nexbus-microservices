
package com.nexbus.bus_route_service;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    security = @SecurityRequirement(name = "ApiKeyAuth")
)
@SecurityScheme(
    name = "ApiKeyAuth",
    type = SecuritySchemeType.APIKEY,
    in = SecuritySchemeIn.HEADER,
    paramName = "X-API-KEY",
    description = "Enter the NexBus API key"
)
public class OpenApiConfig {
}
