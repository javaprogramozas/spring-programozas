package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.services.FileBasedUserService;
import hu.bearmaster.springtutorial.common.services.InMemoryUserService;
import hu.bearmaster.springtutorial.common.services.PostService;
import hu.bearmaster.springtutorial.common.services.UserService;
import hu.bearmaster.springtutorial.common.services.publishers.PublisherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class ProfilesConfig {

    @Profile("!prod")
    @Bean
    public UserService inMemoryUserService() {
        return new InMemoryUserService();
    }

    @Profile("prod")
    @Bean
    public UserService fileBasedUserService(@Value("${users.list}") String userList) {
        return new FileBasedUserService(userList);
    }

    @Bean
    public PostService postService(UserService userService) {
        return new PostService(userService);
    }

    @Bean
    public PublisherService dummyPublisher() {
        return PublisherService.NO_OP;
    }
}
