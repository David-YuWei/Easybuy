/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.easybuy.user.UserService;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "user:userService")
	private UserService userService;
	private String loginURI;

	public AdminInterceptor() {
	}

	public void setLoginURI(String loginURI) {
		this.loginURI = loginURI;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (uri.equals(request.getContextPath() + loginURI)) {
			return true;
		}
		
		Object user = userService.getUser(request);
		if (user == null) {
			redirectLogin(request, response);
			return false;
		} else {
			return true;
		}
	}

	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURI());
		String query = request.getQueryString();
		if (query != null) {
			url.append("?");
			url.append(query);
		}
		response.sendRedirect(request.getContextPath() + loginURI + "?url=" + URLEncoder.encode(url.toString(), "UTF-8"));
	}
}
