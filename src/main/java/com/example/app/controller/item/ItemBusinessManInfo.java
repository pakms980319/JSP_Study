package com.example.app.controller.item;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.service.ItemService;
import com.example.app.domain.item.service.ItemServiceImpl;
import com.example.app.domain.user.dto.BussinessMan;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.dto.User;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class ItemBusinessManInfo implements SubController {
	ConnectionPool_ByHikari connectionPool;
	ItemService itemService;
	UserService userService;
	Session session;
	

	public ItemBusinessManInfo() {
		try {
			connectionPool = ConnectionPool_ByHikari.getInstance();
			itemService = ItemServiceImpl.getInstance();
			userService = UserServiceImpl.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("ItemBusinessManInfo execute()");
		Map<String, Object> result = null;
		HttpSession session = req.getSession();
		String userId = null;
		try {
			String method = req.getMethod();
			if (session.getAttribute("session") != null) {
				Session getSession = (Session) session.getAttribute("session");
				String role = getSession.getRole();
				userId = getSession.getUserId();
				// GET 요청
				if (method.contains("GET")) {
					// 유효성 체크
					if (!(role.equals("BussinessMan"))) {
						req.setAttribute("msg", "상품 리스트조회는 사업자만 가능합니다");
						req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
						return;
					}
					int itemId = Integer.parseInt(req.getParameter("itemId"));
					System.out.println("itemId : " + itemId);
					try {
						Item item = itemService.getItem(itemId);
						}catch (SQLException e) {
							session.setAttribute("msg", "상품이 없습니다. 상품id를 확인해 주세요.");
							req.getRequestDispatcher("/WEB-INF/view/item/itemDetail.jsp").forward(req, resp);
							return;
						}
					
					Item item = itemService.getItem(itemId);
					
					String bussinessManId = item.getBussinessManId();
					BussinessMan bussinessMan = userService.getBussinessMan(bussinessManId);
					userId = bussinessMan.getUserId();
					
					User user = userService.getUser(userId);
					
					String userName = user.getName();
					req.setAttribute("userName", userName);
					req.setAttribute("item", item);
					req.getRequestDispatcher("/WEB-INF/view/item/itemDetail.jsp").forward(req, resp);
					return;
				} else {
					req.setAttribute("msg", "상품리스트등록은 로그인을 하고나서 등록이 가능합니다.");
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}
			}
			
		} catch (Exception e) {
			
		}
	}
}
