package hu.bearmaster.springtutorial.data.jdbc.repository;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class PostUpdate extends SqlUpdate {

    public PostUpdate(DataSource dataSource) {
        super(dataSource, "UPDATE posts SET title = ?, description = ? WHERE id = ?");
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        declareParameter(new SqlParameter("description", Types.VARCHAR));
        declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }

    public int updatePostTitleAndDescriptionById(long id, String title, String description) {
        return update(title, description, id);
    }
}
