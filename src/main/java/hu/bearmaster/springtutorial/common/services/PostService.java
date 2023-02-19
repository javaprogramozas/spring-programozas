package hu.bearmaster.springtutorial.common.services;

import hu.bearmaster.springtutorial.common.model.Post;
import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.model.UserRole;
import hu.bearmaster.springtutorial.common.services.publishers.PublisherService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class PostService implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private final UserService userService;

    private PublisherService publisherService;
    private final List<Post> posts = new ArrayList<>();

    public PostService(UserService userService) {
        this.userService = userService;
        LOGGER.info("Post service constructor called");
    }

    public void createPost(Post post) {
        User author = userService.getUserByName(post.getAuthor().getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + post.getAuthor()));
        if (author.getRole() != UserRole.EDITOR) {
            throw new IllegalArgumentException("User is not an editor");
        }
        post.setAuthor(author);
        posts.add(post);
        LOGGER.info("Created new post {}", post);
        publisherService.notifyUsers(post);
    }

    public List<Post> getPostsByAuthor(User author) {
        return posts.stream()
                .filter(post -> post.getAuthor().equals(author))
                .toList();
    }

    @Autowired
    //@Qualifier("push")
    public void setPublisherService(PublisherService publisherService) {
        LOGGER.info("Publisher service has been injected");
        this.publisherService = publisherService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("Created in context: {}", applicationContext.getDisplayName());
    }

    @PostConstruct
    private void readyToWork() throws Exception {
        LOGGER.info("Post service has been created: {}", publisherService);
    }

    @PreDestroy
    private void itsOverAnakin() throws Exception {
        LOGGER.info("Post service is being destroyed");
    }
}
