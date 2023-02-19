package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.config.ProfilesConfig;
import hu.bearmaster.springtutorial.common.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileBasedBeans {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileBasedBeans.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfilesConfig.class);
        //context.getEnvironment().setActiveProfiles("local");

        LOGGER.info("Default profiles: {}", (Object) context.getEnvironment().getDefaultProfiles());
        LOGGER.info("Active profiles: {}", (Object) context.getEnvironment().getActiveProfiles());
        context.refresh();

        PostService postService = context.getBean(PostService.class);
    }
}
