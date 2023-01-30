package hu.bearmaster.springtutorial.container;

import hu.bearmaster.springtutorial.common.model.Post;
import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.services.PostService;
import hu.bearmaster.springtutorial.common.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class XmlBasedContainer {

    public static void main(String[] args) {
        User user = User.editor("testuser", "pw");
        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user);

        PostService postService = context.getBean(PostService.class);
        List<Post> posts = postService.getPostsByAuthor(user);
        System.out.println(posts);
    }
}
