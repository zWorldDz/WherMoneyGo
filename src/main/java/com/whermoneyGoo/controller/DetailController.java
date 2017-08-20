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
import com.whermoneyGoo.model.User;

/*
  *  DetailController
  */
@WebServlet("/DetailController")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DetailDbUtil detailDbUtil = 	new DetailDbUtil();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Command: "+request.getParameter("command"));
		//System.out.println(request.getParameter("description"));
		//System.out.println(request.getParameter("amount"));
		//System.out.println(request.getParameter("date"));
		
		try {
			if(request.getParameter("command") == null){
				listDetail(request,response);
			}
			else if(request.getParameter("command").equalsIgnoreCase("ADD")){
				System.out.println("DetailController: "+"Before addDetail");
				addDetail(request,response);
				}
			else if(request.getParameter("command").equalsIgnoreCase("REMOVE")){
				System.out.println("DetailController: "+"Before removeDetail");
				removeDetail(request,response);
				}
			else{
				System.out.println("End of DetailController");
				//listDetail(request,response);
				}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void removeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int detailId = Integer.parseInt(request.getParameter("detailId"));//request.getParameter("userId")
		
		System.out.println("DetailController: "+"Start removeDetail");
		detailDbUtil.removeDetail(detailId);
		listDetail(request, response);
		
	}

	private void addDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Read list attribute from request url
		System.out.println("DetailController: "+"Start addDetail");
		String description = request.getParameter("description");
		String amount= request.getParameter("amount");
		String date = request.getParameter("date");
		User userId = new User (Integer.parseInt("1"));//request.getParameter("userId")
		
		Detail theDetail = new Detail( date, amount, description, userId);
		System.out.println("DetailController: "+"Start detailDbUtil");
		detailDbUtil.addPost(theDetail);
		listDetail(request, response);
		
	}

	private void listDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("DetailController: "+"Start listDetail");
			List<Detail>details = detailDbUtil.getDetails();
			request.setAttribute("LIST_DETAIL", details);
			RequestDispatcher rd = request.getRequestDispatcher("/wher-money-project.jsp");
			rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
