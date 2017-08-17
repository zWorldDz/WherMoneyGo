package com.whermoneyGoo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whermoneyGoo.dao.DetailDbUtil;
import com.whermoneyGoo.model.Detail;

/*
  *  DetailController
  */
@WebServlet("/DetailController")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DetailDbUtil detailDbUtil = 	new DetailDbUtil();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("command"));
		listDetail(request,response);
	}

	private void listDetail(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Detail>details = detailDbUtil.getDetails();
			request.setAttribute("LIST_DETAIL", details);
			RequestDispatcher rd = request.getRequestDispatcher("/wher-money-project.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
