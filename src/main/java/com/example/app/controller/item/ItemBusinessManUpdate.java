package com.example.app.controller.item;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		Item item = null;

		try {
			
			String method = req.getMethod(); // 
			int itemId = Integer.parseInt(req.getParameter("itemId"));
			// GET 요청
			if (method.contains("GET")) {
				try {
					item = service.getItem(itemId);
				} catch (SQLException e) {
					connectionPool.txRollBack();
					e.printStackTrace();
					req.setAttribute("msg", "아이템 정보를 불러올 수 없습니다. 관리자에게 문의해주세요.");
					req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					return;
				}
				
				req.setAttribute("item", item);
				req.getRequestDispatcher("/WEB-INF/view/item/ItemBusinessManUpdate.jsp").forward(req, resp);
				return;
			}
			
			// POST 요청 (etc Method) (api 문서 만들기)
			// 01 파라미터 받기
			// 사용자 입력 값 받기 
			HttpSession session = req.getSession();
			
			Session sessionDto = (Session) session.getAttribute("session");
			String id = sessionDto.getUserId();
			
			itemId = Integer.parseInt(req.getParameter("itemId"));
			String itemName = req.getParameter("itemName");
			String itemType = req.getParameter("itemType");
			int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			int itemCount = Integer.parseInt(req.getParameter("itemCount"));
			String manufacturingDateStr = req.getParameter("itemManufacturingDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			
			try {
				 itemManufacturingDate = new Date(dateFormat.parse(manufacturingDateStr).getTime());
				 if (itemManufacturingDate.after(currentTimestamp)) {
					 req.setAttribute("msg", "입력 정보가 잘못되었습니다.");
					 req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
					 return;
				}
			} catch (Exception e) {
			    // 날짜 형식이 잘못된 경우 예외 처리
			    e.printStackTrace();
			}
		
			// 02 유효성 체크
			if (!isValid(itemId) || !isValid(itemName) || !isValid(itemType) || !isValid(itemPrice) || !isValid(itemCount) ||!isValid(manufacturingDateStr)) {
				req.setAttribute("msg", "입력 정보가 잘못되었습니다.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
		
			// 03 서비스 실행
			Map<String, Object> result = null;
			try {
				item = service.getItem(itemId);
				item.setItemName(itemName);
				item.setItemType(itemType);
				item.setItemManufacturingDate(itemManufacturingDate);
				item.setItemCount(itemCount);
				item.setItemPrice(itemPrice);
				result = service.ItemUpdate(itemId, id, item);	
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
				resp.sendRedirect("/item/businessMan/list");
				return;
				
			} else {
				req.setAttribute("msg", "상품 수정에 실패하였습니다 관리자에게 문의해주세요.");
				req.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(req, resp);
				return ;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
