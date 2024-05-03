package com.example.app.controller.item;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.service.ItemService;
import com.example.app.domain.item.service.ItemServiceImpl;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.service.UserService;
import com.example.app.domain.user.service.UserServiceImpl;

public class ItemAdd implements SubController {

	ConnectionPool_ByHikari connectionPool;
	ItemService service;
	Session session;

	public ItemAdd() {
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
		System.out.println("ItemAdd execute()");
		Map<String, Object> result = null;
		HttpSession session = req.getSession();
		Date itemManufacturingDate = null;
		String userId = null;
		int itemPrice = 0;
		int itemCount = 0;
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
						req.setAttribute("msg", "상품등록은 사업자만 가능합니다");
						req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					}
					req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
					return;
				}
			} else {
				req.setAttribute("msg", "상품등록은 로그인을 하고나서 등록이 가능합니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
			}
			
			// 01 파라미터 받기
			String itemName = req.getParameter("itemName");
			String itemType = req.getParameter("itemType");
			try {
			itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			} catch(Exception e) {
				req.setAttribute("NumberFormatExceptionItemPrice", "물건 가격은 숫자만 들어갈 수 있습니다.");
				session.setAttribute("msg", "상품등록에 실패하였습니다.");
				req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
			}
			try {
			itemCount = Integer.parseInt(req.getParameter("itemCount"));
			}catch(Exception e) {
				req.setAttribute("NumberFormatExceptionItemCount", "물건 개수는 숫자만 들어갈 수 있습니다.");
				session.setAttribute("msg", "상품등록에 실패하였습니다.");
				req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
			}
			String manufacturingDateStr = req.getParameter("itemManufacturingDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
			try {
				 itemManufacturingDate = new java.sql.Date(dateFormat.parse(manufacturingDateStr).getTime());
				 if (itemManufacturingDate.after(currentTimestamp)) {
					 req.setAttribute("Manufactur", "제조년일은 현재시간보다 이전시간이어야 합니다.");
					 session.setAttribute("msg", "상품등록에 실패하였습니다.");
					 req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
					 return;
					}
			} catch (Exception e) {
			    // 날짜 형식이 잘못된 경우 예외 처리
			    e.printStackTrace();
			}
			Item item = new Item(itemName, itemType, itemPrice, itemCount, itemManufacturingDate);
			try {
				service.ItemInsert(item, userId);
				connectionPool.txRollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
				// 예외페이지로 넘기기 .. or new ServletException("message")
		}
	}
}
