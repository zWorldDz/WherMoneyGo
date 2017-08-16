package com.whermoneyGoo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.whermoneyGoo.dbSettings.MysqlConnection;
import com.whermoneyGoo.model.User;


public class UserDbUtil {
	
    public List<User> getUsers ()throws Exception{
    	//Set list for all users
    	List <User> users = new ArrayList<User>();
    	//Local ResultSet
    	ResultSet rest = null;
    	
    	try{
    		//Set up Connection
    		MysqlConnection mysqlRest = new MysqlConnection();
    		
    		//Create sql statement
    		String sql = "select * from user order by user_id";
    		
    		// Option can be create or prepare
    		String option = "create";
    		
    		//Pass return value(rest) from ConnectionBuild to local rest
    		rest = mysqlRest.ConnectionBuild(sql,option);
    		
    		//Process result
    		while(rest.next()){
    			int user_id = rest.getInt("user_id");
    			String user_name = rest.getString("user_name");
    			String user_email = rest.getString("user_email");
    			String password = "password";
    			User temp = new User(user_id,user_name,password,user_email);
    			users.add(temp);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//System.out.println("1"+e.getMessage());
    	}finally{
    		close(rest);
    	}
		return users;
    }
	private void close(ResultSet rest) throws Exception {
		if(rest!=null){
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
