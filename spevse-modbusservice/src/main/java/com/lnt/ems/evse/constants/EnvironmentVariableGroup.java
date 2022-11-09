package com.lnt.ems.evse.constants;

public class EnvironmentVariableGroup {
	
	private EnvironmentVariableGroup() {
		throw new IllegalStateException("EnvironmentVariableGroup class");
	}
	public static final String DB_HOST = "DB_HOST";
	public static final String DB_PORT = "DB_PORT";
	public static final String DB_NAMES = "DB_NAMES";
	public static final String DB_USER = "DB_USER";
	public static final String DB_PASSWORD = "DB_PASSWORD";

}
