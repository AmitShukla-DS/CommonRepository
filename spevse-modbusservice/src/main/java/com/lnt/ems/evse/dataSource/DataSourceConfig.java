package com.lnt.ems.evse.dataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lnt.EncryptDecryptUtil;

@Configuration
@EnableJpaRepositories(basePackages = "com.lnt.ems.evse.dao", transactionManagerRef = "transcationManager", entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfig {

	@Bean
	@Primary	// primary is to set default datasource
	@Autowired
	public DataSource dataSource() throws Exception {

		Map<Object , Object> dataSourceMap = new HashMap<>();		
		DataSourceRouting routingDataSource = new DataSourceRouting();

		String dbString = getenv(EnvironmentVariableGroup.DB_NAMES);
		
		String[] dbArray = dbString.split(",");
		for(String dbname:dbArray) {
			System.out.println(dbname);
		}
		for( String dbname : dbArray ) {
			DataSource  DS = createDataSource(dbname);
			dataSourceMap.put(dbname, DS);
		}
		routingDataSource.initDynamicDatasource(dataSourceMap);
		return routingDataSource;
	}


	public DataSource createDataSource(String dbname ) throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + getenv(EnvironmentVariableGroup.DB_HOST) + ":"
				+ getenv(EnvironmentVariableGroup.DB_PORT) + "/" + dbname );
		System.out.println(dbname);
		System.out.println(getPassword());
		dataSource.setUsername(getenv(EnvironmentVariableGroup.DB_USER));
		dataSource.setPassword(getPassword());
		return dataSource;
	}


	// enables datasources for entity manager
	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder)
			throws Exception {
		return builder.dataSource(dataSource()).packages("com.lnt.hmi.usermanagement").build();
	}

	// enables datasources for transcationManager
	@Bean(name = "transcationManager")
	public JpaTransactionManager transactionManager(
			@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}

	// to get environment variables
	private static String getenv(String variable) {
		return ((Function<String, String>) System::getenv).apply(variable);
	}

	// to decrypt password from environment variables
	private String getPassword() throws Exception {
		System.out.println(getenv(EnvironmentVariableGroup.DB_PASSWORD));
		EncryptDecryptUtil encryptDecryptUtil = new EncryptDecryptUtil();
		System.out.println(encryptDecryptUtil.decrypt(getenv(EnvironmentVariableGroup.DB_PASSWORD)));

		return encryptDecryptUtil.decrypt(getenv(EnvironmentVariableGroup.DB_PASSWORD));

	}

}