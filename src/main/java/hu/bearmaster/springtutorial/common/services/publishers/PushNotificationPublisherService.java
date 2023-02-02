package hu.bearmaster.springtutorial.common.services.publishers;

import hu.bearmaster.springtutorial.common.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("push")
public class PushNotificationPublisherService implements PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationPublisherService.class);

    @Override
    public void notifyUsers(Post post) {
        LOGGER.info("Sending push notification for users about new post: '{}' from {}",
                post.getTitle(), post.getAuthor().getUsername());
    }
}
