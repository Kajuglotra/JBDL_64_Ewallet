package org.gfg.NotificationService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
