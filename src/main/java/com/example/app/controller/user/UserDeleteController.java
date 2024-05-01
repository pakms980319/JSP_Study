package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserDeleteController implements SubController {
	ConnectionPool_ByHikari connectionPool;
	UserService service;

	public UserDeleteController() {
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
		try {

			String method = req.getMethod();
			Map<String, Object> result = null;

			// GET 요청
			if (method.contains("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/user/userDeleteChkPW.jsp").forward(req, resp);
				return;
			}
			
			String password = req.getParameter("pw");
			
			HttpSession session = req.getSession();
			
			Session sessionDto = null;
			if (session.getAttribute("session") != null)
				sessionDto = (Session) session.getAttribute("session");
			else {
				req.setAttribute("msg", "로그인 정보를 확인할 수 없습니다. 관리자에게 문의해주세요");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return;
			}
			
			String sessionId = (String) sessionDto.getUserId();
			
			try {
				result = service.deleteUser(sessionId, password);
			} catch (SQLException e) {
				e.printStackTrace();
				connectionPool.txRollBack();
			}
			
			System.out.println("testDelte4");
			
			if (result != null) {
				if ( !(boolean) result.get("response") ) {
					req.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}
				
				session.invalidate();
				
				session = req.getSession();
				
				session.setAttribute("msg", result.get("msg"));
				
				resp.sendRedirect("/");
				return;
			} else {
				req.setAttribute("msg", "로그인 정보를 확인할 수 없습니다. 관리자에게 문의해주세요");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
