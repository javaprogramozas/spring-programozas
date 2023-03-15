package hu.bearmaster.springtutorial.data.jdbc.model;

import java.time.ZonedDateTime;

public class Post {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private ZonedDateTime createdOn;
    
    private int likes;
    
    private String slug;
    
    private Long authorId;
    
    private int comments;

    private String topic;

    private int version;
    
    public Post() {
    }
    
    public Post(String title, String description, ZonedDateTime createdOn, int likes, String slug,
            Long authorId) {
        this(null, title, description, createdOn, likes, slug, authorId);
    }

    public Post(Long id, String title, String description, ZonedDateTime createdOn, int likes, String slug,
            Long authorId) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.likes = likes;
        this.slug = slug;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + "]";
    }
}
