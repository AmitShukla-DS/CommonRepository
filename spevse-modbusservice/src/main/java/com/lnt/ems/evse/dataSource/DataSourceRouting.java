package com.lnt.ems.evse.dataSource;

import static java.lang.System.getenv;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
 
public class DataSourceRouting extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceContext();
	}

	// Map for holding diff datasources
	public void initDynamicDatasource(Map<Object , Object> dataSourceMap) {
		this.setTargetDataSources(dataSourceMap);
		System.out.println("dataSourceMap "+dataSourceMap.toString());
		String dbString = getenv(EnvironmentVariableGroup.DB_NAMES);
		String[] dbArray = dbString.split(",");
		this.setDefaultTargetDataSource(dataSourceMap.get(dbArray[0]));
	}
}
