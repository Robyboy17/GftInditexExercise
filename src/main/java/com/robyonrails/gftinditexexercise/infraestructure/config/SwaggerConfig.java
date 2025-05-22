package com.robyonrails.gftinditexexercise.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de swagger
 *
 * @author Robert Lungu
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Precios API")
                        .version("1.0.0")
                        .description("API para los precios de los productos"));
    }
}
