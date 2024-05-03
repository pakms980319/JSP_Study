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
			String method = req.getMethod();
			if (session.getAttribute("session") != null) {
				Session getSession = (Session) session.getAttribute("session");
				userId = getSession.getUserId();
				// GET 요청
				if (method.contains("GET")) {
					int itemId = Integer.parseInt(req.getParameter("itemId"));
					Item item = null;
					try {
						item = service.getItem(itemId);
					} catch (SQLException e) {
						session.setAttribute("msg", "제거하려는 상품 Id가 없습니다. 상품 Id를 확인해 주세요.");
						req.getRequestDispatcher("/WEB-INF/view/item/itemDetail.jsp").forward(req, resp);
					}
					item = service.getItem(itemId);
					System.out.println("item : " + item);
					session.setAttribute("delete", "다음과 같은 상품을 삭제하시겠습니까?" + item);
					req.getRequestDispatcher("/WEB-INF/view/item/delete.jsp").forward(req, resp);
					return;
				}
			} else {
				req.setAttribute("msg", "상품삭제는 로그인을 하고나서 삭제가 가능합니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
			}

		} catch (Exception e) {

			e.printStackTrace();
			// 예외페이지로 넘기기 .. or new ServletException("message")
		}
	}
}
