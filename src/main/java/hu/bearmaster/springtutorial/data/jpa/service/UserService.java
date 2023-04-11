package hu.bearmaster.springtutorial.data.jpa.service;

import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import hu.bearmaster.springtutorial.data.jpa.repository.UserRepository;
import jakarta.transaction.Transactional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateUserStatusToPending(long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.PENDING);
        userRepository.save(user);
    }
}
