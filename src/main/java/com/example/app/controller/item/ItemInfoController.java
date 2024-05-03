package com.example.app.controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.service.ItemService;
import com.example.app.domain.item.service.ItemServiceImpl;
import com.example.app.domain.user.dto.BussinessMan;
import com.example.app.domain.user.dto.User;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;


public class ItemInfoController implements SubController {
	ConnectionPool_ByHikari connectionPool;
	ItemService itemService;
	UserService userService;

	public ItemInfoController() {
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
		System.out.println("ItemInfoController execute()");

		try {
			// GET 요청
			int itemId = Integer.parseInt(req.getParameter("itemId"));
			Item item = null;
			String businessManId = null;
			BussinessMan businessMan = null;
			String userId = null;
			User user = null;
			String userName = null;

			try {
				item = itemService.getItem(itemId);
				businessManId = item.getBussinessManId();
				businessMan = userService.getBussinessMan(businessManId);
				userId = businessMan.getUserId();
				user = userService.getUser(userId);
				userName = user.getName();
			} catch (Exception e) {
				e.printStackTrace();
				connectionPool.txRollBack();
			}

			if (item != null) {
				req.setAttribute("item", item);
				req.setAttribute("userName", userName);
				req.getRequestDispatcher("/WEB-INF/view/item/itemDetailShow.jsp").forward(req, resp);
				return;
			} else {
				req.setAttribute("msg", "에러가 발생했습니다 관리자에게 문의해주세요.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}