package com.hiberus.config;

import com.hiberus.clients.ClientsPizzas;
import com.hiberus.repositories.UserRepository;
import com.hiberus.services.Impl.UserServiceImpl;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    @Value("${KEYPASS}")
    private String keypass;

    @Bean(name = "KEYPASS")
    public String getKeypass() {
        return this.keypass;
    }

    @Bean
    public UserService userService(UserRepository userRepository, ClientsPizzas clientsPizzas, @Qualifier("KEYPASS") String keypass) {
        return new UserServiceImpl(userRepository, clientsPizzas, keypass);
    }
}
