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
import com.example.app.domain.user.dto.Session;

public class ItemBusinessManDelete implements SubController {

	ConnectionPool_ByHikari connectionPool;
	ItemService service;

	public ItemBusinessManDelete() {
		try {
			connectionPool = ConnectionPool_ByHikari.getInstance();
			service = ItemServiceImpl.getInstance();
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

			Session getSession = (Session) session.getAttribute("session");
			userId = getSession.getUserId();

			int itemId = Integer.parseInt(req.getParameter("itemId"));
			Item item = null;

			try {
				item = service.getItem(itemId);
				result = service.ItemDelete(itemId, userId);
			} catch (SQLException e) {
				connectionPool.txRollBack();
				session.setAttribute("msg", "제거하려는 상품 Id를 확인할 수 없습니다. 관리자에게 문의해주세요");
				resp.sendRedirect("/item/businessMan/delete?itemId=" + itemId);
				return;
			}

			if ((boolean) result.get("response")) {
				session.setAttribute("msg", result.get("msg"));
				resp.sendRedirect("/item/businessMan/list");
				return;
			} else {
				session.setAttribute("msg", result.get("msg"));
				resp.sendRedirect("/item/businessMan/delete?itemId=" + itemId);
				return;
			}

		} catch (Exception e) {

			e.printStackTrace();
			// 예외페이지로 넘기기 .. or new ServletException("message")
		}
	}
}
