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

public class UserInfoController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	UserService service;

	public UserInfoController() {
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
				req.getRequestDispatcher("/WEB-INF/view/user/userInfoChkPW.jsp").forward(req, resp);
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
			String pw = req.getParameter("pw");
			
			// 02 유효성 체크
			if (!isValid(pw)) {
				req.setAttribute("msg", "입력 정보가 잘못되었습니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			// 03 서비스 실행
			Map<String, Object> result = null;
			try {
				String id = service.getSession(sessionId).getUserId();
				User user = service.getUser(id);
				
				if (user.getRole().equals("BussinessMan")) {
					result = service.bussniessManSelect(id, pw);
					req.setAttribute("msg", result.get("msg"));
					req.setAttribute("response", result.get("response"));
					req.setAttribute("user", result.get("user"));
					req.setAttribute("bussinessMan", result.get("bussinessMan"));
				} else {
					result = service.userSelect(id, pw);
					req.setAttribute("msg", result.get("msg"));
					req.setAttribute("response", result.get("response"));
					req.setAttribute("user", result.get("user"));
				}
				
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
				
				req.getRequestDispatcher("/WEB-INF/view/user/userInfo.jsp").forward(req, resp);
				return;
				
			} else {
				req.setAttribute("msg", "회원정보 조회에 실패하였습니다 관리자에게 문의해주세요.");
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
