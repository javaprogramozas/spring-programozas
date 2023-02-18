package hu.bearmaster.springtutorial.common.services;

import hu.bearmaster.springtutorial.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class FileBasedUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedUserService.class);

    private final List<User> users;

    public FileBasedUserService(String path) {
        this.users = initUsersFromFile(path);
    }

    private List<User> initUsersFromFile(String path) {
        LOGGER.info("Reading user list from file {}", path);
        // add implementation here
        return List.of();
    }

    @Override
    public Optional<User> getUserByName(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

    @Override
    public User loginUser(String username, String password) {
        return getUserByName(username)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Incorrect username or password"));
    }

    @Override
    public void registerUser(User user) {
        if (getUserByName(user.getUsername()).isEmpty()) {
            LOGGER.info("Registering user: {}", user);
            users.add(user);
        } else {
            throw new IllegalArgumentException("Username already exists");
        }
    }
}
