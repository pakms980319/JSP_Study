package com.example.app.controller.user;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.User;

public class UserJoinUserSignUpController implements SubController {

	ConnectionPool_ByHikari connectionPool;

	public UserJoinUserSignUpController() {
		try {
			connectionPool = connectionPool.getInstance();
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
				throw new ServletException("회원 가입 오류입니다. 다시 시도해주세요");
			}
			
			// 03 서비스 실행
			User user = new User(userId, password, name, phoneNumber, email, "User");
			

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
	
	public boolean isValid(String obj) {
		if (obj == null)
			return false;
		return true;
	}
}
