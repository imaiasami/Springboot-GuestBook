package com.example.guestbook.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String requestURI = request.getRequestURI();
		String paramId = request.getParameter("member_id") == null ? ""
				: "?member_id=" + request.getParameter("member_id");
		if (session == null || session.getAttribute("login") == null) {
			response.sendRedirect("/members/login?redirectURL=" + requestURI + paramId);
			return false;
		}
		return true;
	}
}
