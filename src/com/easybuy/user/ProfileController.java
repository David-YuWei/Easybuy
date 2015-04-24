package com.easybuy.user;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.user.UserService;

@Controller("profileController")
@RequestMapping("/user/profile")

public class ProfileController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public ProfileController(){
		
	}
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotoLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/user");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/buyer", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView buyerProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_name", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "first_name", required = false) String firstname,
			@RequestParam(value = "middle_name", required = false) String middlename,
			@RequestParam(value = "last_name", required = false) String lastname,
			@RequestParam(value = "email_id", required = false) String emailid,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "phone_no", required = false) String phoneno,
			@RequestParam(value = "url", required = false) String url) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
				
			
				mav.setViewName("/user/myProfile_buyer");
				return mav;
			
				
			}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
}

