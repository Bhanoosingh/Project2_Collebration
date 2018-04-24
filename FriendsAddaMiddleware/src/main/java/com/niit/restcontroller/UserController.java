package com.niit.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.FriendsAdda.DAO.UserDAO;
import com.niit.FriendsAdda.model.UserDetail;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	//------------------CheckLogin-----------------
	@PostMapping(value="/login")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail, HttpSession session)
	{
		System.out.println("Inside user login");
		if(userDAO.checkLogin(userDetail))
		{
			System.out.println("Login True");
			UserDetail tempUserDetail=(UserDetail)userDAO.getUser(userDetail.getEmail());
			userDAO.updateOnlineStatus("Y", tempUserDetail);
			session.setAttribute("userRecord", tempUserDetail);
			return new ResponseEntity<UserDetail>(tempUserDetail,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(userDetail,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// ----------------- Register UserDetail ---------------
	@PostMapping(value = "/registerUser")
	public ResponseEntity<String> registerUserDetail(@RequestBody UserDetail user) {
		
		System.out.println("Inside user registration");
		user.setIsOnline("Not");
		user.setRole("USER");
		if (userDAO.addUser(user)) {
			return new ResponseEntity<String>("UserDetail Registered Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("UserDetail registration failed", HttpStatus.NOT_FOUND);
		}
	}

	// ----------- Update UserDetail -----------------------------
	@PutMapping(value = "/updateUser/{loginname}")
	public ResponseEntity<String> updateUserDetail(@PathVariable("loginname") String loginname, @RequestBody UserDetail userDetail) {
		System.out.println("In updating user " + loginname);
		UserDetail mUserDetail = userDAO.getUser(loginname);
		if (mUserDetail == null) {
			System.out.println("No user found with loginname " + loginname);
			return new ResponseEntity<String>("No user found", HttpStatus.NOT_FOUND);
		}

	
		mUserDetail.setName(userDetail.getName());
		userDAO.updateUser(mUserDetail);
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}

	// --------------------- List UserDetails --------------------------
	@GetMapping(value = "/listUsers")
	public ResponseEntity<List<UserDetail>> listUserDetails() {
		List<UserDetail> listUserDetails = userDAO.listUsers();
		if (listUserDetails.size() != 0) {
			return new ResponseEntity<List<UserDetail>>(listUserDetails, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDetail>>(listUserDetails, HttpStatus.NOT_FOUND);
	}

	// --------------------- Get UserDetail ----------------------
	@GetMapping(value = "/getUser/{loginname}")
	public ResponseEntity<UserDetail> getUserDetail(@PathVariable("loginname") String loginname) {
		UserDetail user = userDAO.getUser(loginname);
		if (user == null) {
			System.out.println("No user found");
			return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deleteUser/{loginname}")
	public ResponseEntity<String> deleteUserDetail(@PathVariable("loginname") String loginname) {
		System.out.println("In delete user" + loginname);
		UserDetail user = userDAO.getUser(loginname);
		if (user == null) {
			System.out.println("No user with LoginName " + loginname + " found to delete");
			return new ResponseEntity<String>("No user found to delete", HttpStatus.NOT_FOUND);
		} else {
			userDAO.deleteUser(user);
			return new ResponseEntity<String>("UserDetail with LoginName " + loginname + " deleted successfully", HttpStatus.OK);
		}
	}
}