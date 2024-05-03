package com.example.app.controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class ItemInfoController implements SubController {
	
	ConnectionPool_ByHikari connectionPool;
	UserService service;

	public ItemInfoController() {
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
		System.out.println("ItemInfoController execute()");
			
			try {
			
				String method = req.getMethod();

				// GET 요청
				if (method.contains("GET")) {
					req.getRequestDispatcher("/WEB-INF/view/item/itemInfo.jsp").forward(req, resp);
					return;
				}

				
			} catch (Exception e) {
				e.printStackTrace();
				
				try {
					connectionPool.txRollBack();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// 예외페이지로 넘기기 .. or new ServletException("message")
			} 
			
	}
	
}
