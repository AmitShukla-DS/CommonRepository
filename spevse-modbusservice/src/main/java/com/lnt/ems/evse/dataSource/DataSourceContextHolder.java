package com.lnt.ems.evse.dataSource;

public class DataSourceContextHolder {

	// enables you to create variables that can only be read and written by the same thread
	// this ensures that variables are local to that thread and cannot be accessible by other threads
	
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void setDataSourceContext(String dataSource) {
		threadLocal.set(dataSource);
	}

	public static String getDataSourceContext() {
		return threadLocal.get();
	}

	public static void clearDataSourceContext() {
		threadLocal.remove();
	}
}
