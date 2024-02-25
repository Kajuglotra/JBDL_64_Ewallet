package org.gfg.UserService.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class KafkaConfig {

//    public KafkaTemplate<String, String> getKafkaTemplate(){
//
//     return new KafkaTemplate<>();
//    }


    @Bean
    public PasswordEncoder getPSEncode(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
