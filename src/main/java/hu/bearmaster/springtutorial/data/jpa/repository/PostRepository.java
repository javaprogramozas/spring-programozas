package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.Post;
import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.EscapeCharacter;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    EscapeCharacter ESCAPE_CHARACTER = EscapeCharacter.of('\\');

    List<Post> findAllByTopicAndLikesGreaterThan(String topic, int likes);

    List<Post> findTop3ByOrderByCreatedOnDesc();

    List<Post> findAllByAuthorStatus(UserStatus status);

    List<Post> findAllByAuthor(User author);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1% OR p.description LIKE %?1%")
    Page<Post> findAllPostsByTitleContainsOrDescriptionContains(String pattern, Pageable pageable);

    List<Post> selectPostsByLikesAndTitle(int likes, String word);

    static Specification<Post> hasTopic(String topic) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("topic"), topic);
    }

    static Specification<Post> titleContains(String titleFragment) {
        String escapedString = ESCAPE_CHARACTER.escape(titleFragment);
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), "%" + escapedString, ESCAPE_CHARACTER.getEscapeCharacter());
    }
}
