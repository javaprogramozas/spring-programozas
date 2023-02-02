package hu.bearmaster.springtutorial.common.model;

import java.util.StringJoiner;

public class Post {

    private String title;

    private String description;

    private User author;

    public Post(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("author=" + author)
                .toString();
    }

    public static Post of(String title, User author) {
        return new Post(title, "<description comes here>", author);
    }
}
