package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.config.JavaConfig;
import hu.bearmaster.springtutorial.common.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycles {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanLifecycles.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        context.registerShutdownHook();
        LOGGER.info("The program is running...");
        PostService postService = context.getBean(PostService.class);

    }

}
