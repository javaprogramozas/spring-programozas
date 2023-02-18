package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.config.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public class EnvironmentAbstraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentAbstraction.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyConfig.class);

        ConfigurableEnvironment environment = context.getEnvironment();

        LOGGER.info("Java home directory: {}", environment.getProperty("JAVA_HOME"));
        LOGGER.info("Current OS user: {}", environment.getProperty("user.name"));
        LOGGER.info("Custom property: {}", environment.getProperty("hello.key"));
        LOGGER.info("Custom source: {}", environment.getProperty("myProp"));
        LOGGER.info("User list: {}", environment.getProperty("users.list"));
    }

}
