package com.whermoneyGoo.junit.userdbutil;

import javax.sql.DataSource;

import org.junit.*;

import com.whermoneyGoo.dao.UserDbUtil;

public class Userdbutil {
	//@Resource(name="jdbc/save_money")
	//private DataSource dataSource;
	
	@Test
	public void test_method_2() throws Exception {
		
		UserDbUtil user = new UserDbUtil();
		System.out.println(user.getUsers().get(0).getUserName());
    }
}
