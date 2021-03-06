package ru.testfield.messagescheduler.config.quartz;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion,
                                 @Value("${springdoc.description}") String appDescription) {
        return new OpenAPI().info(new Info()
                .title("MessageScheduler API")
                .version(appVersion)
                .description(appDescription)
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
