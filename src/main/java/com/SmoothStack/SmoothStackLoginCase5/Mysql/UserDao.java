package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IUserDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.User;

public class UserDao implements IUserDao{
		
	public List<User> findAll(Connection conn){
		List<User> userList = new ArrayList<User>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user;

		String sqlQuery = "SELECT * FROM `userlogin`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	public List<User> findById(Connection conn, int userId){
		List<User> userList = new ArrayList<User>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user;

		String sqlQuery = "SELECT * FROM `userlogin` AS `a` WHERE `a`.`userId` = ?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, userId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
