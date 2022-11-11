package com.lnt.hmi.alerts.dataSource;

import static java.lang.System.getenv;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.lnt.hmi.alerts.dataSource.EnvironmentVariableGroup;

public class DataSourceRouting extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceContext();
	}

	// Map for holding diff datasources
	public void initDynamicDatasource(Map<Object , Object> dataSourceMap) {
		this.setTargetDataSources(dataSourceMap);
		String dbString = getenv(EnvironmentVariableGroup.DB_NAMES);
		String[] dbArray = dbString.split(",");
		this.setDefaultTargetDataSource(dataSourceMap.get(dbArray[0]));
		//this.setDefaultTargetDataSource(dataSourceMap.get("master"));
	}
}
