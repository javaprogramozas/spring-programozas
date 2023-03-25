package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.Post;
import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopicAndLikesGreaterThan(String topic, int likes);

    List<Post> findTop3ByOrderByCreatedOnDesc();

    List<Post> findAllByAuthorStatus(UserStatus status);

    List<Post> findAllByAuthor(User author);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1% OR p.description LIKE %?1%")
    List<Post> findAllPostsByTitleContainsOrDescriptionContains(String pattern);

    List<Post> selectPostsByLikesAndTitle(int likes, String word);
}
