package hu.bearmaster.springtutorial.data.jdbc.model;

import java.util.StringJoiner;

public class Comment {

    private Long id;

    private String username;

    private String body;

    private Long postId;

    public Comment() {
    }

    public Comment(Long id, String username, String body, Long postId) {
        this.id = id;
        this.username = username;
        this.body = body;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("body='" + body + "'")
                .add("postId=" + postId)
                .toString();
    }
}
