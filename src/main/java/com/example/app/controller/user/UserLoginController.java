package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserLoginController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	UserService service;
	Session session;

	public UserLoginController() {
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
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");

			// 02 유효성 체크
			if (!isValid(userId) || !isValid(password)) {
				throw new ServletException("로그인 오류입니다. 다시 시도해주세요");
			}

			Map<String, Object> result = null;
			HttpSession session = req.getSession();
			// 03 서비스 실행

			try {
				result = service.login(userId, password);
			} catch (SQLException e) {
				e.printStackTrace();
				connectionPool.txCommit();
			}

			if (result != null) {
				if (!(boolean) result.get("response")) {
					req.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}

				String name = (String) result.get("name");
				this.session = (Session) result.get("session");

				int sessionId = (int) result.get("sessionId");
				session.setAttribute("session", this.session);
				session.setAttribute("sessionId", sessionId);
				session.setAttribute("name", name);
			}

			// 04 view
			if (result != null)
				if (!(boolean) result.get("response")) {
					req.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}

			session.setAttribute("msg", result.get("msg"));
			// 로그인이 정상적으로 되면 홈으로 리다이렉트
			resp.sendRedirect("/");
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
