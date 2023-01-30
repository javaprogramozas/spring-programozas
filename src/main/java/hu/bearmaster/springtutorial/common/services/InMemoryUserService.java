package hu.bearmaster.springtutorial.common.services;

import hu.bearmaster.springtutorial.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryUserService.class);

    private final List<User> users;

    public InMemoryUserService() {
        this(new ArrayList<>());
    }

    public InMemoryUserService(List<User> users) {
        this.users = users;
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
