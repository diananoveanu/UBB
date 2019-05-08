package client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author diananoveanu
 */

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
