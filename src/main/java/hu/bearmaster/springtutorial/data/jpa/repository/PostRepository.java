package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.Post;
import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopicAndLikesGreaterThan(String topic, int likes);

    List<Post> findTop3ByOrderByCreatedOnDesc();

    List<Post> findAllByAuthorStatus(UserStatus status);

    List<Post> findAllByAuthor(User author);
}
