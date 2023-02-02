package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.model.Post;
import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.services.PostService;
import hu.bearmaster.springtutorial.common.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBasedContainer {

    public static void main(String[] args) {
        User user = User.editor("testuser", "pw");
        Post post = Post.of("Spring is fun", user);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-only.xml");

        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user);

        PostService postService = context.getBean(PostService.class);
        postService.createPost(post);
    }
}
