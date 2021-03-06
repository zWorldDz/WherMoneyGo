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
    
	private void listDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Detail>details = detailDbUtil.getIncomes();
			request.setAttribute("LIST_DETAIL", details);
			
			List<Detail>expenses = detailDbUtil.getExpenses();
			request.setAttribute("LIST_EXPENSE", expenses);
			
			RequestDispatcher rd = request.getRequestDispatcher("/wher-money-project.jsp");
			rd.forward(request, response);
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
	
	private void updateIncome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int  incomeId =Integer.parseInt(request.getParameter("incomeId"));
		String amount = request.getParameter("amount");
		String description = request.getParameter("description");
				
		//create a new obj
		Detail theDetail = new Detail(incomeId,amount,description);
		
		//perform update on db
		detailDbUtil.updateIncome(theDetail);
		//send them back to the list student page
		listDetails(request,response);
		
	}

	private void removeIncome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailController: "+"Start removeDetail");
		detailDbUtil.removeIncome(request.getParameter("incomeId"));
		listDetails(request, response);
		
	}
	
	/*****************************************Servlet doPost*******************************************/    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+++++++++");
		System.out.println("Method: POST");
		System.out.println("Command: "+request.getParameter("command"));
		System.out.println("Symbol: "+request.getParameter("symbol"));
		System.out.println("Income ID: "+ request.getParameter("incomeId"));
		System.out.println("Expense ID: "+ request.getParameter("expenseId"));
		System.out.println("+++++++++");
		
		try {
				if(request.getParameter("command").equalsIgnoreCase("ADD")){
					if(request.getParameter("symbol").equalsIgnoreCase("1")){
						System.out.println("DetailController: "+"Call addIncome() function ");
						addIncome(request,response);
					}else if(request.getParameter("symbol").equalsIgnoreCase("0")){
						System.out.println("DetailController: "+"Call addExpense() function ");
						addExpense(request,response);
					}
				}else if(request.getParameter("command").equalsIgnoreCase("UPDATEINCOME")){
						System.out.println("DetailController: "+"Call updateIncome() function ");
						updateIncome(request,response);
				}
				else if(request.getParameter("command").equalsIgnoreCase("UPDATEEXPENSE")){
						System.out.println("DetailController: "+"Call addExpense() function ");
						updateExpense(request,response);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	private void updateExpense(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int  expenseId =Integer.parseInt(request.getParameter("incomeId"));
		String amount = request.getParameter("amount");
		String description = request.getParameter("description");
				
		//create a new obj
		Detail theDetail = new Detail(expenseId,amount,description);
		
		//perform update on db
		detailDbUtil.updateExpense(theDetail);
		//send them back to the list student page
		listDetails(request,response);
		
	}

	private void addExpense(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Read list attribute from request url
		String description = request.getParameter("description");
		String amount= request.getParameter("amount");
		String date = request.getParameter("date");
		User userId = new User (Integer.parseInt("1"));//request.getParameter("userId")
		
		Detail theDetail = new Detail( date, amount, description, userId);
		System.out.println("DetailController: "+"Start detailDbUtil");
		detailDbUtil.addExpense(theDetail);
		listDetails(request, response);
		
	}
	
	
	
	/*****************************************Servlet doGet*******************************************/    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+++++++++");
		System.out.println("Method: GET");
		System.out.println("Command: "+request.getParameter("command"));
		System.out.println("Symbol: "+request.getParameter("symbol"));
		System.out.println("Income ID: "+ request.getParameter("incomeId"));
		System.out.println("Expense ID: "+ request.getParameter("expenseId"));
		System.out.println("+++++++++");
		//System.out.println(request.getParameter("description"));
		//System.out.println(request.getParameter("amount"));
		//System.out.println(request.getParameter("date"));
		
		try {
			if(request.getParameter("command") == null){
				System.out.println("DetailController: "+"Call listIncome() function ");
				listDetails(request,response);
			}else if(request.getParameter("command").equalsIgnoreCase("REMOVE_INCOME")){
					System.out.println("DetailController: "+"Call REMOVE_INCOME() function ");
					removeIncome(request,response);
			}else if(request.getParameter("command").equalsIgnoreCase("REMOVE_EXPENSE")){
					System.out.println("DetailController: "+"Call REMOVE_EXPENSE() function ");
					removeExpense(request,response);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
		}
	}

	private void removeExpense(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailController: "+"Start removeDetail");
		detailDbUtil.removeExpense(request.getParameter("expenseId"));
		listDetails(request, response);
				
	}
}


