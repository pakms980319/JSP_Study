package com.example.app.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;

public class UserLogoutController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserLogoutController execute()");
		
		try {

			String method = req.getMethod();

			// GET 요청
			if (method.contains("GET")) {
				HttpSession session = req.getSession(false); // 새로운 세션이 생성되지 않도록 false 전달
		        
		        if (session != null) {
		            // 세션의 모든 속성을 삭제
		            session.invalidate();
		            
		        }
		        
		        
		        // 로그아웃 후 이동할 페이지로 리다이렉트
		        resp.sendRedirect("/");
		    }
				return;
	}catch(Exception e) {
		}
	}
	
	
}
