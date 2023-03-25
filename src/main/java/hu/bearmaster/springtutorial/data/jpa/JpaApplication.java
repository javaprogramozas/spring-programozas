package hu.bearmaster.springtutorial.data.jpa;

import hu.bearmaster.springtutorial.data.jpa.config.JpaConfiguration;
import hu.bearmaster.springtutorial.data.jpa.model.Post;
import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import hu.bearmaster.springtutorial.data.jpa.repository.PostRepository;
import hu.bearmaster.springtutorial.data.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JpaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        PostRepository postRepository = context.getBean(PostRepository.class);
        User user = userRepository.findById(3L).orElseThrow();
        LOGGER.info("User: {}", user);

        LOGGER.info("Exists: {}", userRepository.existsByUsername("Juli"));

        List<Post> posts = postRepository.findAllByTopicAndLikesGreaterThan("Útmutató", 10);
        LOGGER.info("Posts: {}", posts);

        LOGGER.info("Most recent post: {}", postRepository.findTop3ByOrderByCreatedOnDesc());

        LOGGER.info("Active user posts: {}", postRepository.findAllByAuthorStatus(UserStatus.ACTIVE));
    }

}
