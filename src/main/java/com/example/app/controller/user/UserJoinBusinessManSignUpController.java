package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.BussinessMan;
import com.example.app.domain.user.dto.User;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserJoinBusinessManSignUpController implements SubController {
	
	ConnectionPool_ByHikari connectionPool;
	UserService service;
	
	public UserJoinBusinessManSignUpController() {
		try {
			connectionPool = ConnectionPool_ByHikari.getInstance();
			service = UserServiceImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserJoinBusinessManSignUpController execute()");
		
		try {
			
			String method = req.getMethod();
			
			if(method.contains("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/user/userJoinBusinessMan.jsp").forward(req, resp);
				return;
			}
		
		// POST 요청 (etc Method) (api 문서 만들기)
		// 01 파라미터 받기
		// 사용자(사업자) 입력 값 받기
		String bussinessManId = req.getParameter("bussinessManId");
		String userId = req.getParameter("userId");
		
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phoneNumber = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		
		// 02 유효성 체크
		if (!isValid(bussinessManId) || !isValid(userId)) {
			throw new ServletException("회원 가입 오류입니다. 다시 시도해주세요.");
		}
		
		if (!isValid(userId) || !isValid(password) || !isValid(name) || !isValid(name) || !isValid(phoneNumber) || !isValid(email)) {
			throw new ServletException("회원 가입 오류입니다. 다시 시도해주세요");
		}
		
		// 03 서비스 실행
		
		BussinessMan bussinessman = new BussinessMan(bussinessManId, userId);
		User user = new User(userId, password, name, phoneNumber, email, "Busssinessman");
		
		Map<String, Object> result = null;
		
		try {
			result = service.signUpBussinessMan(user, bussinessManId);
		} catch (SQLException e) {
			e.printStackTrace();
			connectionPool.txRollBack();
		}
		
		if (result != null) {
			if( !(boolean)result.get("response")) {
				throw new ServletException((String)result.get("msg"));
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("msg", result.get("msg"));
			
		} else {
			throw new ServletException("잘못된 요청처리입니다.");
		}
		
		// 04 view
		
		
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connectionPool.txRollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// 예외페이지로 넘기기 .. or new ServletException("message")
		}
	}

	private boolean isValid(String obj) {
		if (obj == null)
		return false;
		return true;
	}
}
