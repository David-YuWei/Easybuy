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

@Controller("SellerController")
@RequestMapping("/registration/seller")
public class SellerController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public SellerController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotobuyerregistration(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/registration/seller");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView seller(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
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
		String accountnumber = request.getParameter("accountnumber");
		String routingnumber = request.getParameter("routingnumber");
		
		
				String msg = userService.insertSeller(request,firstname,middlename,lastname,emailid,address,phonenumber,username,password,accountnumber,routingnumber);
				if (msg.equals("success")) {
					mav.addObject("msg","<font style=\"color:green;\">You are successfully registered, Please wait for approval</font>");
					mav.setViewName("/login");
					return mav;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
}
	
	
	

