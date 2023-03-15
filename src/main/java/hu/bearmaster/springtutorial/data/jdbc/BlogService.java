package hu.bearmaster.springtutorial.data.jdbc;

import hu.bearmaster.springtutorial.data.jdbc.model.Comment;
import hu.bearmaster.springtutorial.data.jdbc.model.InvalidCommentException;
import hu.bearmaster.springtutorial.data.jdbc.model.Post;
import hu.bearmaster.springtutorial.data.jdbc.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

    private final BlogRepository repository;

    private BlogService self;

    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }

    public Post getPostById(long id) {
        return repository.getPostById(id);
    }

    @Transactional(rollbackFor = InvalidCommentException.class)
    public void addCommentAndUpdateLikes(long postId, String comment, String commenter) throws InvalidCommentException {
        repository.increaseLikesOfPost(postId);

        if (comment == null) {
            throw new InvalidCommentException();
        }

        Comment newComment = new Comment();
        newComment.setBody(comment);
        newComment.setUsername(commenter);

        repository.addNewComment(newComment);
    }

    public void dontDoThisAtHome(long postId) throws InvalidCommentException {
        self.addCommentAndUpdateLikes(postId, "Ez nem fog működni", "bajvan");
    }

    @Autowired
    public void setSelf(BlogService self) {
        this.self = self;
    }
}
