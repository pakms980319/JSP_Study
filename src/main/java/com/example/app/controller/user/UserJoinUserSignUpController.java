package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.User;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserJoinUserSignUpController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	UserService service;

	public UserJoinUserSignUpController() {
		try {
			connectionPool = ConnectionPool_ByHikari.getInstance();
			service = UserServiceImpl.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserJoinUserSignUpController execute()");

		try {

			String method = req.getMethod();

			// GET 요청
			if (method.contains("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/user/userJoin.jsp").forward(req, resp);
				return;
			}

			// POST 요청 (etc Method) (api 문서 만들기)
			// 01 파라미터 받기
			// 사용자 입력 값 받기
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String phoneNumber = req.getParameter("phoneNumber");
			String email = req.getParameter("email");

			// 02 유효성 체크
			if (!isValid(userId) || !isValid(password) || !isValid(name) || !isValid(name) || !isValid(phoneNumber) || !isValid(email)) {
				req.setAttribute("msg", "회원 가입 오류입니다. 다시 시도해주세요");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			// 03 서비스 실행
			User user = new User(userId, password, name, phoneNumber, email, "User");
			Map<String, Object> result = null;
			
			try {
				result = service.signUp(user);
			} catch (SQLException e) {
				e.printStackTrace();
				connectionPool.txRollBack();
			}
			
			if (result != null) {
				if ( !(boolean)result.get("response") )  {
					req.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return ;
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("msg", result.get("msg"));
				
			} else {
				req.setAttribute("msg", "에러가 발생했습니다 관리자에게 문의해주세요.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}

			// 04 view
			resp.sendRedirect("/");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValid(String obj) {
		if (obj == null)
			return false;
		return true;
	}
}
