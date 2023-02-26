package hu.bearmaster.springtutorial.common.services;

import hu.bearmaster.springtutorial.common.model.User;
import hu.bearmaster.springtutorial.common.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBasedUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedUserService.class);

    private final List<User> users;

    public FileBasedUserService(String path) {
        this.users = initUsersFromFile(path);
    }

    public FileBasedUserService(Resource resource) {
        this.users = initUsersFromResource(resource);
    }

    private List<User> initUsersFromFile(String path) {
        LOGGER.info("Reading user list from file {}", path);
        // add implementation here
        return List.of();
    }

    private List<User> initUsersFromResource(Resource resource) {
        LOGGER.info("Reading user list from {}", resource);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.lines()
                    .map(name -> new User(name, "", UserRole.SUBSCRIBER))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Something went wrong!", e);
        }
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
