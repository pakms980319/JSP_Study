package com.example.app.controller.item;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

public class ItemBusinessManUpdate implements SubController {


	ConnectionPool_ByHikari connectionPool;
	ItemService service;

	public ItemBusinessManUpdate() {
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
		System.out.println("ItemUpdate execute()");
		Date itemManufacturingDate = null;
		int itemId = 0;
		int itemPrice = 0;
		int itemCount = 0;
		try {
			
			String method = req.getMethod(); // 
			
			// GET 요청
			if (method.contains("GET")) {
				HttpSession session = req.getSession();
				String userId = ((Session)session.getAttribute("session")).getUserId();
				Item item = service.getItem(itemId); 
				req.setAttribute("item", item);
				req.getRequestDispatcher("/WEB-INF/view/item/ItemBusinessManUpdate.jsp").forward(req, resp);
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
			String id = ((Session) session.getAttribute("session")).getUserId();
			
			
			itemId = Integer.parseInt(req.getParameter("itemId"));
			String itemName = req.getParameter("itemName");
			String itemType = req.getParameter("itemType");
			itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			itemCount = Integer.parseInt(req.getParameter("itemCount"));
			String manufacturingDateStr = req.getParameter("itemManufacturingDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
			try {
				 itemManufacturingDate = new java.sql.Date(dateFormat.parse(manufacturingDateStr).getTime());
				 if (itemManufacturingDate.after(currentTimestamp)) {
					 req.setAttribute("Manufactur", "제조년일은 현재시간보다 이전시간이어야 합니다.");
					 session.setAttribute("msg", "상품 수정에 실패하였습니다.");
					 req.getRequestDispatcher("/WEB-INF/view/item/itemAdd.jsp").forward(req, resp);
					 return;
					}
			} catch (Exception e) {
			    // 날짜 형식이 잘못된 경우 예외 처리
			    e.printStackTrace();
			}
			Item newItem = new Item();
			newItem.setItemName(itemName);
			newItem.setItemType(itemType);
			newItem.setItemPrice(itemPrice);
			newItem.setItemCount(itemCount);
			newItem.setItemManufacturingDate(itemManufacturingDate);
			
			
			// 02 유효성 체크
			if (!isValid(itemId) || !isValid(itemName) || !isValid(itemType) || !isValid(itemPrice) || !isValid(itemCount) ||!isValid(manufacturingDateStr)) {
				req.setAttribute("msg", "입력 정보가 잘못되었습니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
	
			
			// 03 서비스 실행
			Map<String, Object> result = null;
			try {
				result = service.ItemUpdate(itemId, id, newItem);	
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
				
				session.setAttribute("msg", result.get("msg"));
				
				resp.sendRedirect("/");
				return;
				
			} else {
				req.setAttribute("msg", "상품 수정에 실패하였습니다 관리자에게 문의해주세요.");
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

	private boolean isValid(int itemId) {
		return true;
	}
	

	private boolean isValid(String obj) {
		if (obj == null)
			return false;
		return true;
	}
	
	


}
