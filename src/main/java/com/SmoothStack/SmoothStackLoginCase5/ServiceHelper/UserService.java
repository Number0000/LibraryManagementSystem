package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Mysql.UserDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IUserService;
import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IUserDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.*;

public class UserService implements IUserService{
	
	IUserDao userDao;
	Connection conn;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public List<User> getAllUser(){
		 conn = Connections.connection();
		 return userDao.findAll(conn);
	}
	
	public List<User> getUserById(int userId){
		conn = Connections.connection();
		return userDao.findById(conn, userId);
	}
}
