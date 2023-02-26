package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.services.FileBasedUserService;
import hu.bearmaster.springtutorial.common.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceContainer.class);

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        //Resource resource = context.getResource("users.txt");
        //Resource resource = context.getResource("classpath:users.txt");
        //Resource resource = context.getResource("file:external.txt");
        Resource resource = context.getResource("https://raw.githubusercontent.com/javaprogramozas/spring-programozas/master/README.md");

        LOGGER.info("Resource implementation: {}", resource.getClass().getName());
        printFilePath(resource);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            LOGGER.info("Content : {}", lines);
        } catch (IOException e) {
            LOGGER.error("Something went wrong!", e);
        }

        UserService userService = context.getBean(UserService.class);
        User user = userService.getUserByName("Teszt Elek").orElseThrow();
        LOGGER.info("User: {}", user);
    }

    private static void printFilePath(Resource resource) {
        try {
            LOGGER.info("Path: {}", resource.getFile().getAbsolutePath());
        } catch (FileNotFoundException e) {
            LOGGER.info("This resource does not represent a file!");
        } catch (IOException e) {
            LOGGER.error("Something went wrong!", e);
        }
    }

    @Configuration
    @PropertySource("application.properties")
    public static class MyConfig {

        @Bean
        public UserService userService(@Value("classpath:${users.path}") Resource resource) {
            return new FileBasedUserService(resource);
        }
    }
}
