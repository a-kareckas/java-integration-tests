package uk.co.zenitech;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.time.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
public class Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    public Configuration() {}

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Zenitech Integration Tests").version("0.0.1")
            .description("Here you can find a list of Zenitech Integration Tests APIs."));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
