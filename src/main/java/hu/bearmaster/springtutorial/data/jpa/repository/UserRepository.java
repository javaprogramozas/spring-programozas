package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findAllUsersByStatus(UserStatus status);

    @Query("SELECT u FROM User u WHERE (SELECT count(p) FROM Post p WHERE p.author = u) > :count")
    List<User> findAllUsersWithPostsMoreThan(int count);

    @Query(value = "SELECT * FROM users WHERE (SELECT count(p.id) FROM posts p WHERE p.author_id = u.id) > :count", nativeQuery = true)
    List<User> findAllUsersWithPostsMoreThanNative(int count);

    List<User> usersWithPalindromeName();
}
