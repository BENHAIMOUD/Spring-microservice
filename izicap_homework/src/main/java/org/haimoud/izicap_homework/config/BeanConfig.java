package org.haimoud.izicap_homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author AYOUB BENHAIMOUD
 *
 */
@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
