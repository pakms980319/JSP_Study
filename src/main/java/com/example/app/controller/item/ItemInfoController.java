package com.example.app.controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;

public class ItemInfoController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("ItemInfoController execute()");
	}
	
}