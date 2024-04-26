package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserLogoutController implements SubController {

	UserService service;
	ConnectionPool_ByHikari connectionPool;

	public UserLogoutController() {
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
		System.out.println("UserLogoutController execute()");
		Map<String, Object> result = null;
		try {

			HttpSession session = req.getSession(false); // 새로운 세션이 생성되지 않도록 false 전달

			if (session != null) {
				// 세션의 모든 속성을 삭제

				if (session.getAttribute("sessionId") == null) {
					req.setAttribute("msg", "로그인 정보를 확인할 수 없습니다.");
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}

				int sessionId = (int) session.getAttribute("sessionId");

				try {
					result = service.logout(sessionId);
				} catch (SQLException e) {
					e.printStackTrace();
					connectionPool.txRollBack();
				}

				if (result != null) {
					if (!(boolean) result.get("response")) {
						req.setAttribute("msg", result.get("msg"));
						req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
						return;
					}
					session.invalidate(); // 세션 무효화
				} else {
					req.setAttribute("msg", "에러가 발생했습니다 관리자에게 문의해주세요.");
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}

			}

			// 로그아웃 후 이동할 페이지로 리다이렉트
			resp.sendRedirect("/");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
