package com.example.app.controller.user;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.dto.User;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class UserUpdateController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	UserService service;

	public UserUpdateController() {
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

			// GET 요청
			if (method.contains("GET")) {
				HttpSession session = req.getSession();
				String userId = ((Session)session.getAttribute("session")).getUserId();
				User user = service.getUser(userId);
				user.setPassword("");
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/view/user/userUpdate.jsp").forward(req, resp);
				return;
			}

			// POST 요청 (etc Method) (api 문서 만들기)
			// 01 파라미터 받기
			// 사용자 입력 값 받기
			HttpSession session = req.getSession();
			
			if (session.getAttribute("sessionId") == null) {
				req.setAttribute("msg", "로그인 정보를 확인할 수 없습니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			int sessionId = (int) session.getAttribute("sessionId");
			String id = ((Session) session.getAttribute("session")).getUserId();
			String pw = req.getParameter("password");
			String rePw = req.getParameter("repassword");
			String name = req.getParameter("name");
			String phoneNumber = req.getParameter("phoneNumber");
			String email = req.getParameter("email");
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setName(name);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setPassword(rePw);
			
			
			// 02 유효성 체크
			if (!isValid(pw) || !isValid(name) || !isValid(phoneNumber) || !isValid(email) || !isValid(rePw)) {
				req.setAttribute("msg", "입력 정보가 잘못되었습니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			// 03 서비스 실행
			Map<String, Object> result = null;
			try {
				result = service.updateUser(id, pw, newUser);	
			} catch (SQLException e) {
				e.printStackTrace();
				connectionPool.txRollBack();
			}
			
			if (result != null) {
				if ( !(boolean)result.get("response") ) {
					req.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return ;
				}
				
				session.setAttribute("msg", result.get("msg"));
				session.setAttribute("name", name);
				
				resp.sendRedirect("/");
				return;
				
			} else {
				req.setAttribute("msg", "회원정보 수정에 실패하였습니다 관리자에게 문의해주세요.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			
			
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
