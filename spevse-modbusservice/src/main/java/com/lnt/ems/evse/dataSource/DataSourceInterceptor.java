package com.lnt.ems.evse.dataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class DataSourceInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String database = request.getHeader("database");
//		String hostDB = request.getHeader("hostDB");

		if (database != null)
			DataSourceContextHolder.setDataSourceContext(database);
		else
			DataSourceContextHolder.setDataSourceContext("master");

		return super.preHandle(request, response, handler);
	}
}
