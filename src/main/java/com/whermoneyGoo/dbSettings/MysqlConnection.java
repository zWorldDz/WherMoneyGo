package com.whermoneyGoo.dbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;

public class MysqlConnection {
	
	public ResultSet RestConnectionBuild(String sql,String option) throws Exception{
		//System.out.println("==============");
		//System.out.println("RestConnectionBuild...");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		
    	//For caches rows(Result Set) in memory
    	CachedRowSet rowset = new CachedRowSetImpl();

		try{
	    // JDBC Driver
	    Class.forName("com.mysql.jdbc.Driver");
	        
	    // Setup the connection with the DB
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/save_money?user=root&password=root");
	    
	    //Create Statement or Prepare Statement, it based on the sql query
	    if(option.equalsIgnoreCase("create"))
	    	stmt = conn.createStatement();
	    else if(option.equalsIgnoreCase("prepare"))
	    	stmt = conn.prepareStatement(sql);
	    
	    //Run the query
    	rest = stmt.executeQuery(sql);
    	
    	//Pass result set to rowset due to connection(conn, stmt, rest) needed to be close
    	rowset.populate(rest);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn,stmt,rest);
		}
		//returning cahce data(Result set)
		return rowset;
	}
	
public PreparedStatement StatementConnectionBuild(String sql,Connection conn) throws Exception{
		//System.out.println("==============");
		//System.out.println("StatementConnectionBuild ...");
		
		PreparedStatement stmt = null;

		try{
	    // JDBC Driver
	    Class.forName("com.mysql.jdbc.Driver");
	        
	    // Setup the connection with the DB
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/save_money?user=root&password=root");
	    
	   stmt = conn.prepareStatement(sql);
    	
		}catch(Exception e){
			e.printStackTrace();
		}
		//returning cahce data(Result set)
		//System.out.println("==============");
		return stmt;
		
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rest) throws Exception {
		//System.out.println("==============");
		
		if(conn!=null){
			conn.close();
			//System.out.println("Conn Closed in MysqlConnection...");
		}
		if(stmt!=null){
			stmt.close();
			//System.out.println("Stmt Closed in MysqlConnection...");
		}
		if(rest!=null){
			rest.close();
			//System.out.println("Rest Closed in MysqlConnection...");
		}
		//System.out.println("==============");
	}
}
