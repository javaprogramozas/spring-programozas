package hu.bearmaster.springtutorial.data.jdbc;

import hu.bearmaster.springtutorial.data.jdbc.model.Comment;
import hu.bearmaster.springtutorial.data.jdbc.model.InvalidCommentException;
import hu.bearmaster.springtutorial.data.jdbc.model.Post;
import hu.bearmaster.springtutorial.data.jdbc.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class BlogService {

    private final BlogRepository repository;

    private final TransactionTemplate transactionTemplate;

    private final PlatformTransactionManager transactionManager;

    private BlogService self;

    public BlogService(BlogRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public Post getPostById(long id) {
        return repository.getPostById(id);
    }

    @Transactional(rollbackFor = InvalidCommentException.class)
    public void addCommentAndUpdateLikes(long postId, String comment, String commenter) throws InvalidCommentException {
        addCommentAndUpdateLikesInternal(postId, comment, commenter);
    }

    public Comment addCommentAndUpdateLikesWithTransactionTemplate(
            long postId, String comment, String commenter) throws InvalidCommentException {
        return transactionTemplate.execute(status -> {
            try {
                return addCommentAndUpdateLikesInternal(postId, comment, commenter);
            } catch (InvalidCommentException e) {
                status.setRollbackOnly();
            }
            return null;
        });
    }

    public Comment addCommentAndUpdateLikesWithTransactionManager(
            long postId, String comment, String commenter) throws InvalidCommentException {

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        Comment newComment = null;
        try {
            try {
                newComment = addCommentAndUpdateLikesInternal(postId, comment, commenter);
            } catch (InvalidCommentException e) {
                transactionManager.rollback(status);
                throw e;
            }
            transactionManager.commit(status);
        } finally {
            if (!status.isCompleted()) {
                transactionManager.rollback(status);
            }
        }
        return newComment;
    }

    private Comment addCommentAndUpdateLikesInternal(long postId, String comment, String commenter) throws InvalidCommentException {
        repository.increaseLikesOfPost(postId);

        if (comment == null) {
            throw new InvalidCommentException();
        }

        Comment newComment = new Comment();
        newComment.setBody(comment);
        newComment.setUsername(commenter);

        repository.addNewComment(newComment);
        return newComment;
    }

    public void dontDoThisAtHome(long postId) throws InvalidCommentException {
        self.addCommentAndUpdateLikes(postId, "Ez nem fog működni", "bajvan");
    }

    @Autowired
    public void setSelf(BlogService self) {
        this.self = self;
    }
}
