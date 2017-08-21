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
		System.out.println("+++++++++");
		System.out.println("Method: GET");
		System.out.println("Command: "+request.getParameter("command"));
		System.out.println("Symbol: "+request.getParameter("symbol"));
		System.out.println("Income ID: "+ request.getParameter("incomeId"));
		System.out.println("+++++++++");
		//System.out.println(request.getParameter("description"));
		//System.out.println(request.getParameter("amount"));
		//System.out.println(request.getParameter("date"));
		
		try {
			if(request.getParameter("command") == null){
				System.out.println("DetailController: "+"Call listIncome() function ");
				listDetails(request,response);
			}else if(request.getParameter("command").equalsIgnoreCase("REMOVE")){
				System.out.println("DetailController: "+"Call removeIncome() function ");
				removeIncome(request,response);
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

	private void removeIncome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailController: "+"Start removeDetail");
		detailDbUtil.removeIncome(request.getParameter("incomeId"));
		listDetails(request, response);
		
	}

	private void listDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Detail>details = detailDbUtil.getIncomes();
			request.setAttribute("LIST_DETAIL", details);
			
			List<Detail>expenses = detailDbUtil.getExpenses();
			request.setAttribute("LIST_EXPENSE", expenses);
			
			RequestDispatcher rd = request.getRequestDispatcher("/wher-money-project.jsp");
			rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+++++++++");
		System.out.println("Method: POST");
		System.out.println("Command: "+request.getParameter("command"));
		System.out.println("Symbol: "+request.getParameter("symbol"));
		System.out.println("+++++++++");
		try {
				if(request.getParameter("command").equalsIgnoreCase("ADD")){
					if(request.getParameter("symbol").equalsIgnoreCase("1")){
						System.out.println("DetailController: "+"Call addIncome() function ");
						addIncome(request,response);
					}else{
						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	private void addIncome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Read list attribute from request url
		String description = request.getParameter("description");
		String amount= request.getParameter("amount");
		String date = request.getParameter("date");
		User userId = new User (Integer.parseInt("1"));//request.getParameter("userId")
		
		Detail theDetail = new Detail( date, amount, description, userId);
		System.out.println("DetailController: "+"Start detailDbUtil");
		detailDbUtil.addIncome(theDetail);
		listDetails(request, response);
	}
	}


