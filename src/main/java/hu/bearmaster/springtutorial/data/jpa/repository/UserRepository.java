package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findAllUsersByStatus(UserStatus status);

}
