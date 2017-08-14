package com.whermoneyGoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.whermoneyGoo.model.User;


public class UserDbUtil {
	//@Resource(name="jdbc/save_money")
	//private DataSource dataSource;
	
    //public UserDbUtil(DataSource theDataSource) {
	//	dataSource = theDataSource;
   // }
	
    public List<User> getUsers ()throws Exception{
    	//Set list for all users
    	List <User> users = new ArrayList<User>();
    	//Declare db connection
    	//Connection conn = null;
    	Connection connect = null;
    	Statement stmt = null;
    	ResultSet rest = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/save_money?"
                            + "user=root&password=root");
    		//Get Connection
    		//conn = dataSource.getConnection();
    		
    		//Create sql statement
    		String sql = "select * from user order by user_id";
    		stmt = connect.createStatement();
    		
    		//Execute query
    		rest = stmt.executeQuery(sql);
    		
    		//Process result
    		while(rest.next()){
    			int user_id = rest.getInt("user_id");
    			String user_name = rest.getString("user_name");
    			String user_email = rest.getString("user_email");
    			String password = "password";
    			User temp = new User(user_id,user_name,user_email,password);
    			users.add(temp);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//System.out.println("1"+e.getMessage());
    	}finally{
    		close(connect,stmt,rest);
    	}
		return users;
    }
	private void close(Connection connect, Statement stmt, ResultSet rest) throws Exception {
		if(connect!=null){
			connect.close();
		}if(stmt!=null){
			stmt.close();
		}if(rest!=null){
			rest.close();
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
