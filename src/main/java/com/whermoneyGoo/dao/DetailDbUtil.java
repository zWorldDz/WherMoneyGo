package com.whermoneyGoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.whermoneyGoo.dbSettings.MysqlConnection;
import com.whermoneyGoo.model.Detail;
import com.whermoneyGoo.model.User;

public class DetailDbUtil {
	
	/*****************************************Income DAO Start*******************************************/
	
	/*
	 *  Default Load Data to the jsp
	 */
    public List<Detail> getIncomes ()throws Exception{
    	//Set list for all users
    	List <Detail> details = new ArrayList<Detail>();
    	//Local ResultSet
    	ResultSet rest = null;
    	
    	try{
    		//Set up Connection
    		MysqlConnection mysqlRest = new MysqlConnection();
    		
    		//Create sql statement
    		String sql = "select * from Income order by income_id";
    		
    		// Option can be create or prepare
    		String option = "create";
    		
    		//Pass return value(rest) from ConnectionBuild to local rest
    		rest = mysqlRest.RestConnectionBuild(sql, option);
    		
    		//Process result
    		while(rest.next()){
    			int detail_id = Integer.parseInt(rest.getString("income_id"));
    			String date = rest.getString("income_date");
    			String amount = rest.getString("income_amount");
    			String description = rest.getString("income_desc");
    			User user = new User(rest.getInt("user_id"));
    			Detail temp = new Detail(detail_id,date,amount,description,user);
    			details.add(temp);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//System.out.println("1"+e.getMessage());
    	}finally{
    		close(null,null,rest);
    	}
		return details;
    }
    
	/*
	 *  Add New income
	 */
	public void addIncome(Detail theDetail) throws Exception {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			//Set up Connection
    		MysqlConnection mysqlStmt = new MysqlConnection();
    		
    		
			String sql = "insert into income(income_date, income_amount, income_desc, user_id)values(?, ?, ?, ?)";
    		stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
    		
			stmt.setString(1, theDetail.getDate());
			stmt.setString(2, theDetail.getAmount());
			stmt.setString(3, theDetail.getDescription());
			stmt.setInt(4, theDetail.getUser().getUserId());
			
			stmt.execute();
		}finally{
			close(conn,stmt,null);
		}
		
	}
	
	/*
	 *  Update exist income
	 */
	public void updateIncome(Detail theDetail) throws Exception{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		//get db connection
		MysqlConnection mysqlStmt = new MysqlConnection();
		
		//Create SQL update statement
		String sql = "update Income set income_desc=?, income_amount=?  where income_id=?";
		
		//prepare statement
		stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
		
		//Set params
		stmt.setString(1, theDetail.getDescription());
		stmt.setString(2, theDetail.getAmount());
		stmt.setInt(3, theDetail.getDetail_id());
		//execute sql statement
		stmt.execute();
	}
	
	/*
	 *  Remove exist income
	 */
	public void removeIncome(String incomeId) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			//Set up Connection
    		MysqlConnection mysqlStmt = new MysqlConnection();
    		
			String sql = "DELETE FROM Income WHERE income_id=? ";
    		stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
			stmt.setString(1,incomeId);
			System.out.println("IN"+incomeId);
			stmt.execute();
		}finally{
			close(conn,stmt,null);
		}
		
	}
	
	/*****************************************Income DAO End*******************************************/    
	
	
    /*****************************************Expense DAO Start*******************************************/    
		public List<Detail> getExpenses ()throws Exception{
    	//Set list for all users
    	List <Detail> details = new ArrayList<Detail>();
    	//Local ResultSet
    	ResultSet rest = null;
    	
    	try{
    		//Set up Connection
    		MysqlConnection mysqlRest = new MysqlConnection();
    		
    		//Create sql statement
    		String sql = "select * from Expense order by expense_id";
    		
    		// Option can be create or prepare
    		String option = "create";
    		
    		//Pass return value(rest) from ConnectionBuild to local rest
    		rest = mysqlRest.RestConnectionBuild(sql, option);
    		
    		//Process result
    		while(rest.next()){
    			int detail_id = Integer.parseInt(rest.getString("expense_id"));
    			String date = rest.getString("expense_date");
    			String amount = rest.getString("expense_amount");
    			String description = rest.getString("expense_desc");
    			User user = new User(rest.getInt("user_id"));
    			//System.out.print(detail_id+" ");
    			//System.out.print(date+" ");
    			//System.out.print(amount+" ");
    			//System.out.print(description+" ");
    			//System.out.print(user+" ");
    			Detail temp = new Detail(detail_id,date,amount,description,user);
    			details.add(temp);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//System.out.println("1"+e.getMessage());
    	}finally{
    		close(null,null,rest);
    	}
		return details;
    }
		
		/*
		 *  Add New income
		 */
		public void addExpense(Detail theDetail) throws Exception {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try{
				//Set up Connection
	    		MysqlConnection mysqlStmt = new MysqlConnection();
	    		
	    		
				String sql = "insert into expense(expense_date, expense_amount, expense_desc, user_id)values(?, ?, ?, ?)";
	    		stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
	    		
				stmt.setString(1, theDetail.getDate());
				stmt.setString(2, theDetail.getAmount());
				stmt.setString(3, theDetail.getDescription());
				stmt.setInt(4, theDetail.getUser().getUserId());
				
				stmt.execute();
			}finally{
				close(conn,stmt,null);
			}
			
		}
		
		/*
		 *  Update exist income
		 */
		public void updateExpense(Detail theDetail) throws Exception{
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			//get db connection
			MysqlConnection mysqlStmt = new MysqlConnection();
			
			//Create SQL update statement
			String sql = "update Expense set expense_desc=?, expense_amount=?  where expense_id=?";
			
			//prepare statement
			stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
			
			//Set params
			stmt.setString(1, theDetail.getDescription());
			stmt.setString(2, theDetail.getAmount());
			stmt.setInt(3, theDetail.getDetail_id());
			//execute sql statement
			stmt.execute();
		}
		/*
		 *  Remove exist income
		 */
		public void removeExpense(String expenseId) throws Exception {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try{
				//Set up Connection
	    		MysqlConnection mysqlStmt = new MysqlConnection();
	    		
				String sql = "DELETE FROM Expense WHERE expense_id=? ";
	    		stmt =   mysqlStmt.StatementConnectionBuild(sql,conn);
				stmt.setString(1,expenseId);
				System.out.println("IN"+expenseId);
				stmt.execute();
			}finally{
				close(conn,stmt,null);
			}
			
		}
	/*****************************************Expense DAO End*******************************************/    
    
	/*
	 *  Close Db Network Function	
	 */
	
	private void close(Connection conn, Statement stmt, ResultSet rest) throws Exception {
		//System.out.println("In DetailDbUtil");

		if(conn!=null){
			conn.close();
			//System.out.println("Conn Closed in DetailDbUtil...");
		}
		if(stmt!=null){
			stmt.close();
			//System.out.println("Stmt Closed in DetailDbUtil...");
		}
		if(rest!=null){
			rest.close();
			//System.out.println("Rest Closed in DetailDbUtil...");
		}
	}
	
	
	/* 
	 *  Testing
	 * 
    public List<User> getUsers(){
    	
	List <User> users = new ArrayList<>();

	int user_id = 1;
	String user_name = "First";
	String user_email = "First@gmail.com";
	String password = "password";
	User temp = new User(user_id,user_name,user_email,password);
	users.add(temp);
	
	return users;
    }*/

}
