package hu.bearmaster.springtutorial.data.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("application.properties")
@ComponentScan("hu.bearmaster.springtutorial.data.jdbc")
public class DatabaseConfiguration {

    @Bean
    public DataSource hikariDataSource(Environment environment) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.setUsername(environment.getProperty("database.username"));
        config.setPassword(environment.getProperty("database.password"));
        config.setSchema(environment.getProperty("database.schema"));
        config.setMaximumPoolSize(environment.getProperty("database.maxpoolsize", Integer.class, 5));
        return new HikariDataSource(config);
    }

}
