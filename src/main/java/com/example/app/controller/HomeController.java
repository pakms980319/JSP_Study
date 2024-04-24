package com.example.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.domain.user.dao.UserDao;
import com.example.app.domain.user.dao.UserDaoImpl;

public class HomeController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("HomeController's execute() invoke..");
		
		try {			 
			req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
