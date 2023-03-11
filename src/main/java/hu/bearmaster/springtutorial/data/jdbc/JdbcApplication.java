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
        String title = repository.getPostTitle();

        LOGGER.info("Post title: {}", title);
    }
}
