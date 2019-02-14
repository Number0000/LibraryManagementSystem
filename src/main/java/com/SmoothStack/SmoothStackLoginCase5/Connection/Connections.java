package com.SmoothStack.SmoothStackLoginCase5.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

		public static Connection connection(){
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mydb", "root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}

}
