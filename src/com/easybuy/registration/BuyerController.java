package com.easybuy.registration;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.user.UserService;

@Controller("BuyerController")
@RequestMapping("/registration/buyer")
public class BuyerController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public BuyerController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotobuyerregistration(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/registration/buyerregistration");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		ModelAndView mav = new ModelAndView();
		try {
		String firstname= request.getParameter("firstname");
		String middlename= request.getParameter("middlename");
		String lastname= request.getParameter("lastname");
		String emailid= request.getParameter("emailid");
		String address= request.getParameter("address");
		String phonenumber= request.getParameter("phonenumber");
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		String cpassword= request.getParameter("cpassword");
		
		
		
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(firstname) || StringUtils.isBlank(lastname)
					|| StringUtils.isBlank(emailid) || StringUtils.isBlank(address) || StringUtils.isBlank(phonenumber) || StringUtils.isBlank(cpassword)) {
				mav.addObject("msg", "Please enter all details");
				mav.setViewName("/registration/buyerregistration");
				return mav;
			}else if(!cpassword.equals(password))
			{
				mav.addObject("msg", "Confirm password does not match with Password.Please enter again");
				mav.setViewName("/registration/buyerregistration");
				return mav;
				
			}
			else{
				String msg = userService.insertBuyer(request,firstname,middlename,lastname,emailid,address,phonenumber,username,password);
				if (msg.equals("success")) {
					mav.addObject("msg","<font style=\"color:green;\">You are successfully registered</font>");
					mav.setViewName("/login");
					return mav;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
}
	
	
	

