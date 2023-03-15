package hu.bearmaster.springtutorial.data.jdbc.repository;

import hu.bearmaster.springtutorial.data.jdbc.model.Post;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.ZoneOffset;

public class PostQuery extends MappingSqlQuery<Post> {

    public PostQuery(DataSource dataSource) {
        super(dataSource, "SELECT * FROM posts WHERE id = ?");
        declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }

    @Override
    protected Post mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
