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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

        LOGGER.info("Users with more than 3 posts: {}", userRepository.findAllUsersWithPostsMoreThan(3));

        //LOGGER.info("Posts with pattern 'szám': {}", postRepository.findAllPostsByTitleContainsOrDescriptionContains("15%"));

        //sortingExample(postRepository);
        pagingExample(postRepository);
    }

    private static void sortingExample(PostRepository postRepository) {

        List<Post> posts = postRepository.findAll(Sort.by("createdOn"));

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            LOGGER.info("#{} {} ({} - {})", i + 1, post.getTitle(), post.getTopic(), post.getCreatedOn());
        }
    }

    private static void pagingExample(PostRepository postRepository) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Post> postPage = postRepository.findAllPostsByTitleContainsOrDescriptionContains("teszt", pageable);

        while (true) {
            List<Post> posts = postPage.getContent();
            LOGGER.info("Page {} of {}", postPage.getNumber(), postPage.getTotalPages());
            for (int i = 0; i < posts.size(); i++) {
                Post post = posts.get(i);
                LOGGER.info("#{} {}", i + 1, post.getTitle());
            }
            if (postPage.hasNext()) {
                postPage = postRepository.findAllPostsByTitleContainsOrDescriptionContains("teszt", postPage.nextPageable());
            } else {
                break;
            }
        }
    }
}
