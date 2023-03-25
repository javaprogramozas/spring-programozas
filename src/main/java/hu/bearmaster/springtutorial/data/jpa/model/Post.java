package hu.bearmaster.springtutorial.data.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@NamedQueries({
        @NamedQuery(name = "Post.selectPostsByLikesAndTitle",
                query = """
                    SELECT p
                    FROM Post p
                    WHERE p.likes > :likes
                    AND LOCATE(:word, p.title) > 0
                    ORDER BY p.id DESC
                    """)
})
@Entity
@Table(name = "posts", schema = "blogs")
@SequenceGenerator(name = "postIdGenerator", sequenceName = "posts_seq", schema = "blogs", initialValue = 1, allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postIdGenerator")
    private Long id;

    private String title;

    private String description;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    private int likes;

    private String slug;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String topic;
    
    public Post() {
    }
    
    public Post(String title, String description, ZonedDateTime createdOn, int likes, String slug) {
        this(null, title, description, createdOn, likes, slug);
    }

    public Post(Long id, String title, String description, ZonedDateTime createdOn, int likes, String slug) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.likes = likes;
        this.slug = slug;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + "]";
    }
}
