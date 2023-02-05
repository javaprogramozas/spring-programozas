package hu.bearmaster.springtutorial.common.services;

import hu.bearmaster.springtutorial.common.model.Post;
import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.model.UserRole;
import hu.bearmaster.springtutorial.common.services.publishers.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private final UserService userService;

    private final PublisherService publisherService;
    private final List<Post> posts = new ArrayList<>();

    public PostService(UserService userService, PublisherService publisherService) {
        this.userService = userService;
        this.publisherService = publisherService;
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
}
