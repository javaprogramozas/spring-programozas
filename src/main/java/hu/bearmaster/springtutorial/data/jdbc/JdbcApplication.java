package hu.bearmaster.springtutorial.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);

        BlogRepository repository = context.getBean(BlogRepository.class);

        LOGGER.info("Cím: {}", repository.getPostTitle());
        LOGGER.info("Bejegyzések száma: {}", repository.numberOfPostsInTopic("Érdekesség"));
        LOGGER.info("Bejegyzés id alapján: {}", repository.getPostById(16));

        for (Post post : repository.getAllPosts()) {
            LOGGER.info("{}", post);
        }

        repository.increaseLikesOfPost(16);
        LOGGER.info("Bejegyzés id alapján: {}", repository.getPostById(16));

        long id = repository.addNewPost(newPost());
        LOGGER.info("Új bejegyzés id-ja: {}", id);

        int maxLikes = repository.getMaximumLikesInTopic("Útmutató");
        LOGGER.info("Max. like-ok száma: {}", maxLikes);
    }

    private static Post newPost() {
        Post post = new Post();
        post.setTitle("Új bejegyzés");
        post.setDescription("Ez megint valami teszt, mi?");
        post.setSlug("/spring-jdbc-test");
        return post;
    }
}
