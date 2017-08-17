package com.whermoneyGoo.dbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;

public class MysqlConnection {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rest = null;
	
	public ResultSet RestConnectionBuild(String sql,String option) throws Exception{
    	
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
	
	private void close(Connection conn, Statement stmt, ResultSet rest) throws Exception {
		if(conn!=null){
			conn.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		if(rest!=null){
			rest.close();
		}
	}
}
