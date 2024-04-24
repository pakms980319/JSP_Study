package com.example.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private Map<String, SubController> map;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("FrontController init() invoke..");
	
		map = new HashMap<String, SubController>();
		String path = config.getServletContext().getContextPath();
		
		// '/'
		map.put(path + "/", new HomeController());
		
		// book
//		map.put(path + "/book/add", new BookAddController());
//		map.put(path + "/book/read", new BookReadController());
//		map.put(path + "/book/list", new BookListController());
//		map.put(path + "/book/update", new BookUpdateController());
//		map.put(path + "/book/delete", new BookDeleteController());
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("FrontController service() invoke.." + uri);
		
		//UTF-8
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		SubController controller = map.get(uri);  // null Check 필요
		
		if (controller == null) {
			throw new ServletException("해당 페이지는 존재하지 않습니다..");
		}
		
		controller.execute(req, resp);
	}

}
