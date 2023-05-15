package be.poliscrypts.contactmanagerapp.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ManageContact API")
                        .description("App to manage contacts in a company")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
