package com.niit.FriendsAdda.DAO;

import java.util.List;

import com.niit.FriendsAdda.model.UserDetail;

public interface UserDAO {
public boolean addUser(UserDetail user);
public boolean deleteUser(UserDetail user);
public boolean updateUser(UserDetail user);
public UserDetail getUser(int userId);
public List<UserDetail> listUser();

}