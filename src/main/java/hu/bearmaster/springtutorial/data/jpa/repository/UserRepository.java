package hu.bearmaster.springtutorial.data.jpa.repository;

import hu.bearmaster.springtutorial.data.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
