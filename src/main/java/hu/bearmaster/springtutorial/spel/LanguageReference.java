package hu.bearmaster.springtutorial.spel;

import hu.bearmaster.springtutorial.common.model.Post;
import hu.bearmaster.springtutorial.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class LanguageReference {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageReference.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(LanguageReference.MyConfig.class);
    }

    @Configuration
    public static class MyConfig {

        //@Bean
        public Object literals(
                @Value("#{'Hello SpEL'}") String aString,
                @Value("#{11}") int anInt,
                @Value("#{3.1415}") double aDouble,
                @Value("#{null}") Object aNullValue) {

            LOGGER.info("String: {}", aString);
            LOGGER.info("int: {}", anInt);
            LOGGER.info("double: {}", aDouble);
            LOGGER.info("null: {}", aNullValue);

            return new Object();
        }

        //@Bean
        public Object references(
                @Value("#{myPost}") Post post,
                @Value("#{myPost.title}") String postTitle,
                @Value("#{myPost.author.username}") String postAuthorName,
                @Value("#{myPost.labels[1]}") String secondLabel,
                @Value("#{allUsers[0]}") User firstUser,
                @Value("#{postsMap['best']}") Post bestPost
        ) {
            LOGGER.info("Post: {}", post);
            LOGGER.info("Post title: {}", postTitle);
            LOGGER.info("Post author name: {}", postAuthorName);
            LOGGER.info("Post 2nd label: {}", secondLabel);
            LOGGER.info("1st user: {}", firstUser);
            LOGGER.info("Best post: {}", bestPost);

            return new Object();
        }

        //@Bean
        public Object operators(
                @Value("#{5 == 0}") boolean b1,
                @Value("#{allUsers.size() < 4}") boolean b2,
                @Value("#{'autumn' < 'spring'}") boolean b3,
                @Value("#{myPost < null}") boolean b4,
                @Value("#{myPost instanceof T(Integer)}") boolean b5,
                @Value("#{'apple' matches '.+e$'}") boolean b6
        ) {
            LOGGER.info("b1: {}", b1);
            LOGGER.info("b2: {}", b2);
            LOGGER.info("b3: {}", b3);
            LOGGER.info("b4: {}", b4);
            LOGGER.info("b5: {}", b5);
            LOGGER.info("b6: {}", b6);
            return new Object();
        }

        //@Bean
        public Object types(
                @Value("#{T(String)}") Class<?> stringType,
                @Value("#{T(java.time.LocalDate)}") Class<?> localDateType,
                @Value("#{T(java.time.ZoneOffset).UTC}") ZoneOffset zone,
                @Value("#{T(hu.bearmaster.springtutorial.common.model.User).subscriber('test')}") User testUser,
                @Value("#{new java.util.ArrayList()}") List newList
        ) {
            LOGGER.info("String type: {}", stringType);
            LOGGER.info("Localdate type: {}", localDateType);
            LOGGER.info("Zone: {}", zone);
            LOGGER.info("Test user: {}", testUser);
            LOGGER.info("New list: {}", newList);
            return new Object();
        }

        @Bean
        public Object misc(
                @Value("#{postWithoutTitle.title != null ? postWithoutTitle.title : 'Untitled'}") String ternary,
                @Value("#{postWithoutTitle.title ?: 'Untitled'}") String elvis,
                @Value("#{postWithoutTitle.title?.toUpperCase()}") String safe,
                @Value("#{postWithoutTitle.title = 'New title'}") String newTitle,
                @Value("#{postWithoutTitle}") Post postWithoutTitle
        ) {
            LOGGER.info("'Ternary' title: {}", ternary);
            LOGGER.info("'Elvis' title: {}", elvis);
            LOGGER.info("'Safe' title: {}", safe);
            LOGGER.info("New title: {}", newTitle);
            LOGGER.info("postWithoutTitle.title: {}", postWithoutTitle.getTitle());
            return new Object();
        }

        @Bean
        public Post myPost() {
            User user = User.editor("Mr. Editor", "pw");
            Post post = Post.of("SpEL post", user);
            post.setLabels(new String[]{"fun", "programming", "Spring"});
            return post;
        }

        @Bean
        public List<User> allUsers() {
            return List.of(
                    User.editor("John", "pw"),
                    User.subscriber("Maria")
            );
        }

        @Bean
        public Map<String, Post> postsMap() {
            User user = User.editor("Mr. Editor", "pw");
            Post bestPost = Post.of("Best post ever", user);
            Post longestPost = Post.of("Longest post here", user);
            return Map.of(
                    "best", bestPost,
                    "longest", longestPost
            );
        }

        @Bean
        public Post postWithoutTitle() {
            return Post.of("Something", User.editor("John Doe", "****"));
        }

    }

}
