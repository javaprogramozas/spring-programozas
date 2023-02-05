package hu.bearmaster.springtutorial.common.config;

import hu.bearmaster.springtutorial.common.services.publishers.EmailPublisherService;
import hu.bearmaster.springtutorial.common.services.publishers.PushNotificationPublisherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PublisherConfig {

    @Bean("email")
    @Primary
    public EmailPublisherService emailPublisherService() {
        return new EmailPublisherService();
    }

    @Bean({"push", "pushService"})
    public PushNotificationPublisherService pushNotificationService() {
        return new PushNotificationPublisherService();
    }
}
