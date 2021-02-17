package ecard.com.batch;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class DataSourceConfiguration {

	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

	@Value("${spring.datasource.hikari.jdbc-url}")
	private static String dbURL;

	@Value("${spring.datasource.hikari.username}")
	private static String userName;

	@Value("${spring.datasource.hikari.password}")
	private String password;

	static {
		config.setJdbcUrl(dbURL);
		config.setUsername(userName);
		config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
