package com.example.guestbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.guestbook.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	// 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/",
				"/guestbooks/list", "/members/join", "/members/login", "/error", "/**/*.js",
				"/**/*.css");
	}
}
