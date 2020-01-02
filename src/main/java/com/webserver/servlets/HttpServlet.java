package com.webserver.servlets;

import java.io.File;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

/**
 * 所有Servlet的超类
 * @author ta
 *
 */
public abstract class HttpServlet {
	public abstract void service(HttpRequest request,HttpResponse response);
	/**
	 * 跳转到指定路径
	 * 注:TOMCAT中实际该方法属于转发器，可以
	 * 通过request获取。
	 * @param path
	 * @param request
	 * @param response
	 */
	public void forward(String path,HttpRequest request,HttpResponse response) {
		response.setEntity(new File("webapps"+path));
	}
	
}






