package com.gft.retail.manager.config;

import javax.sql.DataSource;

/**
 * Interface of datasource to be used deending of the implementation
 * 
 * @author Roberto Salazar - GFT
 */
public interface DataSourceConfig {

	DataSource dataSource();
	
}