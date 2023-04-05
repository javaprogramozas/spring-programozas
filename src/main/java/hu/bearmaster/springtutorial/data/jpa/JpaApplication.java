package hu.bearmaster.springtutorial.data.jpa;

import hu.bearmaster.springtutorial.data.jpa.config.JpaConfiguration;
import hu.bearmaster.springtutorial.data.jpa.model.Post;
import hu.bearmaster.springtutorial.data.jpa.model.User;
import hu.bearmaster.springtutorial.data.jpa.model.UserStatus;
import hu.bearmaster.springtutorial.data.jpa.repository.PostRepository;
import hu.bearmaster.springtutorial.data.jpa.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class JpaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        PostRepository postRepository = context.getBean(PostRepository.class);
        User user = userRepository.findById(3L).orElseThrow();
        LOGGER.info("User: {}", user);

        LOGGER.info("Exists: {}", userRepository.existsByUsername("Juli"));

        List<Post> posts = postRepository.findAllByTopicAndLikesGreaterThan("Útmutató", 10);
        LOGGER.info("Posts: {}", posts);

        LOGGER.info("Most recent post: {}", postRepository.findTop3ByOrderByCreatedOnDesc());

        LOGGER.info("Active user posts: {}", postRepository.findAllByAuthorStatus(UserStatus.ACTIVE));

        LOGGER.info("Users with more than 3 posts: {}", userRepository.findAllUsersWithPostsMoreThan(3));

        //LOGGER.info("Posts with pattern 'szám': {}", postRepository.findAllPostsByTitleContainsOrDescriptionContains("15%"));

        //sortingExample(postRepository);
        //pagingExample(postRepository);

        //queryByExample(userRepository, postRepository);
        predicates(postRepository, context.getBean(EntityManager.class));
    }

    private static void sortingExample(PostRepository postRepository) {

        List<Post> posts = postRepository.findAll(Sort.by("createdOn"));

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            LOGGER.info("#{} {} ({} - {})", i + 1, post.getTitle(), post.getTopic(), post.getCreatedOn());
        }
    }

    private static void pagingExample(PostRepository postRepository) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Post> postPage = postRepository.findAllPostsByTitleContainsOrDescriptionContains("teszt", pageable);

        while (true) {
            List<Post> posts = postPage.getContent();
            LOGGER.info("Page {} of {}", postPage.getNumber(), postPage.getTotalPages());
            for (int i = 0; i < posts.size(); i++) {
                Post post = posts.get(i);
                LOGGER.info("#{} {}", i + 1, post.getTitle());
            }
            if (postPage.hasNext()) {
                postPage = postRepository.findAllPostsByTitleContainsOrDescriptionContains("teszt", postPage.nextPageable());
            } else {
                break;
            }
        }
    }

    private static void queryByExample(UserRepository userRepository, PostRepository postRepository) {
        User user = new User();
        user.setStatus(UserStatus.PENDING);
        user.setUsername("Ga");

        ExampleMatcher userMatcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<User> userExample = Example.of(user, userMatcher);

        List<User> pendingUsers = userRepository.findAll(userExample);

        LOGGER.info("Pending users: {}", pendingUsers);

        Post post = new Post();
        post.setTopic("Érdekesség");
        post.setTitle("?");

        ExampleMatcher postMatcher = ExampleMatcher.matching()
                .withIgnorePaths("likes")
                .withMatcher("topic", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", matcher -> matcher.endsWith());
        Example<Post> postExample = Example.of(post, postMatcher);

        List<Post> posts = postRepository.findAll(postExample);
        postRepository.findBy(postExample, query -> query.count());

        LOGGER.info("Posts by example: {}", posts);

    }

    private static void predicates(PostRepository postRepository, EntityManager entityManager) {
        // SELECT p FROM Post p WHERE p.topic = :topic AND p.description LIKE %:pattern%
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Post> query = builder.createQuery(Post.class);
//        Root<Post> root = query.from(Post.class);
//        query.select(root);
//        Predicate predicate = builder.and(
//                builder.equal(root.get("topic"), "Érdekesség"),
//                builder.like(root.get("title"), "%Mi%"));
//        query.where(predicate);
//
//        List<Post> posts = entityManager.createQuery(query).getResultList();

        Specification<Post> specification = PostRepository.hasTopic("Érdekesség")
                .and(PostRepository.titleContains("15%"));
        List<Post> posts = postRepository.findAll(specification);

        LOGGER.info("Posts by criteria: {}", posts);
    }
}
