package com.whermoneyGoo.model;

public class User {
		private int userId;
		private String userName;
		private String userPassword;
		private String userEmail;
		
		public User(int userId) {
			super();
			this.userId = userId;
		}

		public User(int userId, String userName, String userPassword, String userEmail) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userPassword = userPassword;
			this.userEmail = userEmail;
		}
		
		public User(String userName, String userPassword, String userEmail) {
			super();
			this.userName = userName;
			this.userPassword = userPassword;
			this.userEmail = userEmail;
		}


		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
					+ ", userEmail=" + userEmail + "]";
		}
		
}

