package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.config.JavaConfig;
import hu.bearmaster.springtutorial.common.config.LazyConfig;
import hu.bearmaster.springtutorial.common.lazy.Chicken;
import hu.bearmaster.springtutorial.common.lazy.Dog;
import hu.bearmaster.springtutorial.common.lazy.Egg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextAwareContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextAwareContainer.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);

        Chicken chicken = context.getBean(Chicken.class);
        Egg egg = context.getBean(Egg.class);

        LOGGER.info("{}", chicken);
        LOGGER.info("{}", egg);
        LOGGER.info("{}", context.getBean(Dog.class));

    }

}
