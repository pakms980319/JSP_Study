package com.example.app.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.app.domain.user.dto.Session;
import com.example.app.filter.type.Role;

public class PermissionFilter implements Filter {
	Map<String, Role> pageGradeMap = new HashMap<String, Role>();
	
	// 최초 한번만 실행되는 메서드
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String contextPath = filterConfig.getServletContext().getContextPath();
		
		pageGradeMap.put(contextPath + "/user/logout", Role.ROLE_USER);
		pageGradeMap.put(contextPath + "/user/info", Role.ROLE_USER);
		pageGradeMap.put(contextPath + "/user/update", Role.ROLE_USER);
		pageGradeMap.put(contextPath + "/user/delete", Role.ROLE_USER);
		
		pageGradeMap.put(contextPath + "/item/businessMan/search", Role.ROLE_BUSINESSMAN);
		pageGradeMap.put(contextPath + "/item/businessMan/info", Role.ROLE_BUSINESSMAN);
		pageGradeMap.put(contextPath + "/item/businessMan/update", Role.ROLE_BUSINESSMAN);
		pageGradeMap.put(contextPath + "/item/businessMan/delete", Role.ROLE_BUSINESSMAN);
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 전
		System.out.println("Permission Filter Start ...");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		// 접속한 유저의 Role
		Session sessionDto = (Session) session.getAttribute("session"); 
		
		String role = null;
		if (sessionDto != null)
			role = sessionDto.getRole();
		
		Role userRole = null;
		
		if (role != null)
			switch (role) {
		        case "User":
		        	userRole = Role.ROLE_USER;
		        	break;
		        case "BussinessMan":
		        	userRole = Role.ROLE_BUSINESSMAN;
		        	break;
		        case "Admin":
		        	userRole = Role.ROLE_ADMIN;
		        	break;
			}
		
		
		if(userRole == null) {
			// 비로그인 상태
			throw new ServletException("익명 계정으로는 접근이 불가능한 페이지입니다.");
		}
		
		// 로그인 상태
		String reqUri = request.getRequestURI();
		
		Role pageRole = pageGradeMap.get(reqUri);
		
		if (pageRole != null) {
			if (userRole.ordinal() < pageRole.ordinal()) {
				// 권한 부족
				throw new ServletException("해당 권한으로는 접근이 불가능한 페이지입니다.");
			}
		}
		
		chain.doFilter(req, resp);
		System.out.println("Permission Filter End ...");
		// 후
		
	}
	
}
