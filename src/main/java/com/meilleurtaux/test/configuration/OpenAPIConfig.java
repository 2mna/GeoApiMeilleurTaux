package com.meilleurtaux.test.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${meilleurtaux.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");


        Contact myContact = new Contact();
        myContact.setName("Amna Belhassen");
        myContact.setEmail("amnabelhassen7@gmail.com");


        Info info = new Info()
                .title("Geo service")
                .version("1.0")
                .description("This API exposes endpoints to get towns name and population corresponding to the given" +
                        "postal code .")
                .contact(myContact);
        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
