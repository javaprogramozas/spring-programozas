package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.services.FileBasedUserService;
import hu.bearmaster.springtutorial.common.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class PropertyConfig {

    @Bean
    public UserService userService(@Value("${users.list}") String userList) {
        return new FileBasedUserService(userList);
    }

}
