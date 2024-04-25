package com.example.app.controller.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;

public class UserLoginController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	
	public UserLoginController() {
		try {
			connectionPool = ConnectionPool_ByHikari.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserLoginController execute()");

		try {

			String method = req.getMethod();

			// GET 요청
			if (method.contains("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
				return;
			}

			// POST 요청 (etc Method) (api 문서 만들기)
			// 01 파라미터 받기


			// 02 유효성 체크


			// 03 서비스 실행
	

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

}
