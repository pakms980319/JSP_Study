package com.example.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.item.ItemBusinessManDelete;
import com.example.app.controller.item.ItemBusinessManInfo;
import com.example.app.controller.item.ItemBusinessManSearch;
import com.example.app.controller.item.ItemBusinessManUpdate;
import com.example.app.controller.item.ItemInfoController;
import com.example.app.controller.item.ItemSearchController;
import com.example.app.controller.user.UserDeleteController;
import com.example.app.controller.user.UserInfoController;
import com.example.app.controller.user.UserJoinBusinessManSignUpController;
import com.example.app.controller.user.UserJoinController;
import com.example.app.controller.user.UserJoinUserSignUpController;
import com.example.app.controller.user.UserLoginController;
import com.example.app.controller.user.UserLogoutController;
import com.example.app.controller.user.UserUpdateController;

public class FrontController extends HttpServlet {
	private Map<String, SubController> map;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("FrontController init() invoke..");
	
		map = new HashMap<String, SubController>();
		String path = config.getServletContext().getContextPath();
		
		// '/'	
		map.put(path + "/", new HomeController());
		
		// user
		map.put(path + "/user/delete", new UserDeleteController());
		map.put(path + "/user/info", new UserInfoController());
		map.put(path + "/user/join/businessMan", new UserJoinBusinessManSignUpController());
		map.put(path + "/user/join", new UserJoinController());
		map.put(path + "/user/join/user", new UserJoinUserSignUpController());
		map.put(path + "/user/login", new UserLoginController());
		map.put(path + "/user/logout", new UserLogoutController());
		map.put(path + "/user/update", new UserUpdateController());
		
		// item
		map.put(path + "/item/businessMan/delete", new ItemBusinessManDelete());
		map.put(path + "/item/businessMan/info", new ItemBusinessManInfo());
		map.put(path + "/item/businessMan/search", new ItemBusinessManSearch());
		map.put(path + "/item/businessMan/update", new ItemBusinessManUpdate());
		map.put(path + "/item/info", new ItemInfoController());
		map.put(path + "/item/search", new ItemSearchController());
		
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
