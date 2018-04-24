package com.niit.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.FriendsAdda.DAO.ProfilePicDAO;
import com.niit.FriendsAdda.model.ProfilePicture;
import com.niit.FriendsAdda.model.UserDetail;



@RestController
public class UploadPicController {

	@Autowired
	ProfilePicDAO profileDAO;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadPicture(@RequestParam(value="file")CommonsMultipartFile fileupload,HttpSession session)
	{

		UserDetail userDetail=(UserDetail)session.getAttribute("userRecord");
		System.out.println("Inside Profile picture controller : "+userDetail.getEmail());
		
		if(userDetail==null) 
		{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else
		{
			System.out.println("Uploading the picture..");
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setLoginname(userDetail.getEmail());
			profilePicture.setImage(fileupload.getBytes());
			profileDAO.save(profilePicture);
			System.out.println("Successfully uploaded..!!");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{loginname}")
	public @ResponseBody byte[] getProfilePic(@PathVariable("loginname") String loginname)
	{
		
		ProfilePicture profilePicture=profileDAO.getProfilePicture(loginname);
		
		if(profilePicture==null)
		{
			return null;
		}
		else
		{
			return profilePicture.getImage();
		}
}
}