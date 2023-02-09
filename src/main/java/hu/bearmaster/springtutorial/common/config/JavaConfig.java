package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.services.InMemoryUserService;
import hu.bearmaster.springtutorial.common.services.PostService;
import hu.bearmaster.springtutorial.common.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan("hu.bearmaster.springtutorial.common.services")
@Import(PublisherConfig.class)
public class JavaConfig {

    //@Autowired
    //private PublisherService publisherService;

    @Bean
    public UserService userService() {
        return new InMemoryUserService();
    }

    @Bean
    public PostService postService() {
        return new PostService(userService());
    }

}
