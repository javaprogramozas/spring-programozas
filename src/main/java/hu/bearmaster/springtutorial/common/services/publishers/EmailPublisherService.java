package hu.bearmaster.springtutorial.common.services.publishers;

import hu.bearmaster.springtutorial.common.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("email")
public class EmailPublisherService implements PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailPublisherService.class);

    @Override
    public void notifyUsers(Post post) {
        LOGGER.info("Emailing users about new post: '{}' from {}", post.getTitle(), post.getAuthor().getUsername());
    }
}
