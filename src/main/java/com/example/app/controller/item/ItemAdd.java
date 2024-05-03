package com.example.app.controller.item;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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

			Session getSession = (Session) session.getAttribute("session");
			String role = getSession.getRole();
			userId = getSession.getUserId();
			
			
			// GET 요청
			if (method.contains("GET")) {
				// 유효성 체크
				req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
				return;
			}

			
			// POST
			// 01 파라미터 받기
			String itemName = req.getParameter("itemName");
			String itemType = req.getParameter("itemType");
			
			try {
				itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			} catch (Exception e) {
				req.setAttribute("NumberFormatExceptionItemPrice", "물건 가격은 숫자만 들어갈 수 있습니다.");
				
				session.setAttribute("msg", "상품등록에 실패하였습니다.");
				req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
				return;
			}
			
			try {
				itemCount = Integer.parseInt(req.getParameter("itemCount"));
			} catch (Exception e) {
				req.setAttribute("NumberFormatExceptionItemCount", "물건 개수는 숫자만 들어갈 수 있습니다.");
				
				session.setAttribute("msg", "상품등록에 실패하였습니다.");
				req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
				return;
			}
			
			String manufacturingDateStr = req.getParameter("itemManufacturingDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			
			try {
				itemManufacturingDate = new Date(dateFormat.parse(manufacturingDateStr).getTime());
				
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
				result = service.ItemInsert(item, userId);
			} catch (SQLException e1) {
				connectionPool.txRollBack();
				e1.printStackTrace();
			}
			
			if (result != null) {
				if ( (Boolean) result.get("response") ) {
					session.setAttribute("msg", result.get("msg"));
					resp.sendRedirect("/item/businessMan/list");
					return;
				} else {
					session.setAttribute("msg", result.get("msg"));
					req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
					return;
				}
			} else {
				req.setAttribute("msg", "에러가 발생했습니다 관리자에게 문의해주세요.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}

		} catch (Exception e) {

			e.printStackTrace();
			// 예외페이지로 넘기기 .. or new ServletException("message")
		}
	}
}
