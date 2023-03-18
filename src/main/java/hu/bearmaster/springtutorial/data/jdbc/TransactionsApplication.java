package hu.bearmaster.springtutorial.data.jdbc;

import hu.bearmaster.springtutorial.data.jdbc.config.DatabaseConfiguration;
import hu.bearmaster.springtutorial.data.jdbc.model.InvalidCommentException;
import hu.bearmaster.springtutorial.data.jdbc.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsApplication.class);

    public static void main(String[] args) throws InvalidCommentException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BlogService blogService = context.getBean(BlogService.class);
        LOGGER.info("Class={}", blogService.getClass());

        Post post = blogService.getPostById(49);
        LOGGER.info("Like-ok száma: {}", post.getLikes());

        //blogService.addCommentAndUpdateLikes(49, null, "Sanyi");
        //blogService.dontDoThisAtHome(49);
        blogService.addCommentAndUpdateLikesWithTransactionManager(49, "valami rossz", "Béla");

        post = blogService.getPostById(49);
        LOGGER.info("Like-ok száma: {}", post.getLikes());
    }

}
