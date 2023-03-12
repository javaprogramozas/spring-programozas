package hu.bearmaster.springtutorial.data.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class BlogRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public BlogRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    // Egy oszlopos eredmény
    public String getPostTitle() {
        return jdbcTemplate.queryForObject("SELECT title FROM posts WHERE id = 8", String.class);
    }

    // SQL paraméter
    public int numberOfPostsInTopic(String topic) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM posts WHERE topic = ?", Integer.class, topic);
    }

    // row mapper
    public Post getPostById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM posts WHERE id = ?", this::mapPost, id);
    }

    // több rekord lekérdezése
    public List<Post> getAllPosts() {
        return jdbcTemplate.query("SELECT * FROM posts", this::mapPost);
    }

    // INSERT, UPDATE, DELETE
    public void increaseLikesOfPost(long id) {
        jdbcTemplate.update("UPDATE posts SET likes = likes + 1 WHERE id = ?", id);
    }

    // generált kulcsok megszerzése
    public long addNewPost(Post post) {
        String sql = """
                     INSERT INTO posts \
                     (title, description, created_on, slug, topic, version) \
                     VALUES (?, ?, ?, ?, 'Spring', 1) \
                     RETURNING id
                     """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setTimestamp(3, Timestamp.from(ZonedDateTime.now().toInstant()));
            statement.setString(4, post.getSlug());
            return statement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public long addNewPost2(Post post) {
        SimpleJdbcInsert insertPost = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("posts")
                .usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_on", "slug", "topic", "version");
        Map<String, ?> parameters = Map.of(
                "title", post.getTitle(),
                "description", post.getDescription(),
                "created_on", Timestamp.from(ZonedDateTime.now().toInstant()),
                "slug", post.getSlug(),
                "topic", "Spring",
                "version", 1
        );
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(post);
        return insertPost.executeAndReturnKey(parameterSource).longValue();
    }

    // tetszőleges utasítás végrehajtása
    public void createNewTable() {
        jdbcTemplate.execute("CREATE TABLE test_data (id BIGINT, data VARCHAR(255))");
    }

    // nevesített SQL paraméter
    public int getMaximumLikesInTopic(String topic) {
        //Map<String, ?> parameters = Map.of("topic", topic);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("topic", topic);
        return namedJdbcTemplate.queryForObject("SELECT max(likes) FROM posts WHERE topic = :topic", parameters, Integer.class);
    }

    private Post mapPost(ResultSet resultSet, int rowNum) throws SQLException {
        return new Post(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getTimestamp("created_on").toInstant().atZone(ZoneOffset.UTC),
                resultSet.getInt("likes"),
                resultSet.getString("slug"),
                resultSet.getLong("author_id")
        );
    }

}
