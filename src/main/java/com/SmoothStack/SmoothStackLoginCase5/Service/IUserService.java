package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;
import com.SmoothStack.SmoothStackLoginCase5.Entity.User;

public interface IUserService {

	public List<User> getAllUser();
	
	public List<User> getUserById(int userId);

}
