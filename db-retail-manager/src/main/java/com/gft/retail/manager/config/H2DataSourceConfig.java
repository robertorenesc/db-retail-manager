package com.gft.retail.manager.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Datasource implementation to connect to H2 in memory Database
 * 
 * @author Roberto Salazar - GFT
 */
@Configuration
@Profile(value="h2")
public class H2DataSourceConfig implements DataSourceConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	@FlywayDataSource
    @Override
	public DataSource dataSource() {
		logger.info("-------> Initializing Test H2:");

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("db_test")
                .build();
        return db;
	}

	@Bean(destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        Server dbServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        dbServer.start();
        return dbServer;
    }
	
}
