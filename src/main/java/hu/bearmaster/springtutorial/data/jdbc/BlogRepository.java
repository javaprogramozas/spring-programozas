package hu.bearmaster.springtutorial.data.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class BlogRepository {

    private final JdbcTemplate jdbcTemplate;

    public BlogRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getPostTitle() {
        return jdbcTemplate.queryForObject("SELECT title FROM posts WHERE id = 8", String.class);
    }

}
