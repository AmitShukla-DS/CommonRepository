/**
 * EVSE is part of L&T SPARK Digital Energy Platform (c)2021-2024, L&T ECC (PT&D
 * Digital Solutions, and its affiliates and assigns and licensors All rights
 * reserved L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **//*
	 * package com.lnt.hmi.alerts.config;
	 * 
	 * import java.util.function.Function;
	 * 
	 * import javax.sql.DataSource;
	 * 
	 * import org.springframework.boot.jdbc.DataSourceBuilder; import
	 * org.springframework.context.annotation.Bean; import
	 * org.springframework.context.annotation.Configuration;
	 * 
	 * import com.lnt.EncryptDecryptUtil; import
	 * com.lnt.hmi.alerts.constants.EnvironmentVariableGroup;
	 * 
	 * @Configuration public class JpaConfig {
	 * 
	 * @Bean public DataSource getDataSource() throws Exception{
	 * 
	 * EncryptDecryptUtil encryptDecryptUtil = new EncryptDecryptUtil(); String
	 * dbPass = encryptDecryptUtil.decrypt(getenv(EnvironmentVariableGroup.
	 * ESS_EVSE_DB_PASSWORD));
	 * 
	 * //@SuppressWarnings("rawtypes") DataSourceBuilder<?> dataSourceBuilder =
	 * DataSourceBuilder.create();
	 * dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
	 * dataSourceBuilder.url("jdbc:mysql://"+getenv(EnvironmentVariableGroup.
	 * ESS_EVSE_DB_HOST)+":"+getenv(EnvironmentVariableGroup.ESS_EVSE_DB_PORT)+"/"+
	 * getenv(EnvironmentVariableGroup.ESS_EVSE_DB_NAME));
	 * dataSourceBuilder.username(getenv(EnvironmentVariableGroup.ESS_EVSE_DB_USER))
	 * ; dataSourceBuilder.password(dbPass); return dataSourceBuilder.build(); }
	 * 
	 * private static String getenv(String variable) { return ((Function<String,
	 * String>) System::getenv).apply(variable); }
	 * 
	 * }
	 */