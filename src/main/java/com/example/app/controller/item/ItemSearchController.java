package com.example.app.controller.item;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.item.dto.Criteria;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.dto.PageDto;
import com.example.app.domain.item.service.ItemService;
import com.example.app.domain.item.service.ItemServiceImpl;

public class ItemSearchController implements SubController {

	ConnectionPool_ByHikari connectionPool;
	ItemService service;

	public ItemSearchController() {
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
		System.out.println("ItemSearchController execute : ");
		System.out.println("");

		String pageNo  = req.getParameter("pageNo");
		System.out.println("pageNo : " + pageNo);
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		System.out.println("ItemSearchController execute  type : " + type);
		System.out.println("ItemSearchController execute  keyword : " + keyword);

		Criteria criteria=null;
		if(pageNo==null) {
			if(type!=null && keyword!=null)
				criteria = new Criteria(type,keyword); //키워드 전체 조회
			else
				criteria = new Criteria(); //키워드x 기본 조회 1페이지 10개
		}
		else {
			if(type!=null && keyword!=null)
				criteria = new Criteria(Integer.parseInt(pageNo),10,type,keyword); //키워드 전체 조회
			else
				criteria = new Criteria(Integer.parseInt(pageNo),10);	 //키워드x 기본 조회 n번째
			
			
		}
        			
		//유효성
		//서비스
		System.out.println("ItemSearchController execute criteria : " + criteria);
		Map<String,Object> returnValue = null;
		
		try {
			returnValue = service.getAllItems(criteria);			
			
		} catch (Exception e) {
			e.printStackTrace();
			//05-01 TX
			try {connectionPool.txRollBack();} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		
		
		List<Item> list =  (List<Item>) returnValue.get("list");
		PageDto pageDto = (PageDto)returnValue.get("pageDto");
			
		//뷰
		req.setAttribute("list", list);
		req.setAttribute("pageDto", pageDto);

		System.out.println("ItemSearchController execute list : " + list);
		System.out.println("ItemSearchController execute pageDto : " + pageDto);


		System.out.println("");
		try {
			
			req.getRequestDispatcher("/WEB-INF/view/item/list.jsp").forward(req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
	}
	
}
