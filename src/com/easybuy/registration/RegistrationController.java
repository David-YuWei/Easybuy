package com.easybuy.registration;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.user.UserService;

@Controller("RegistrationController")
@RequestMapping("/registration")
public class RegistrationController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public RegistrationController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotobuyerregistration(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/registration");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/check", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			String username= request.getParameter("username");
			
			String msg = userService.checkUsername(request,username);
			
			model.put("msg", msg);
			model.put("status", "success");
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	} 
	
	@RequestMapping(value = "/buyer", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView buyer(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
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
		
		
		String msg = userService.insertBuyer(request,firstname,middlename,lastname,emailid,address,phonenumber,username,password);
				if (msg.equals("success")) {
					mav.addObject("msg","You are successfully registered");
					mav.setViewName("/login");
					return mav;
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotosellerregistration(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/registration/");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/seller", method = {RequestMethod.POST,RequestMethod.GET})
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
		String accountnumber = request.getParameter("accountnumber");
		String routingnumber = request.getParameter("routingnumber");
		
		
		
			
				String msg = userService.insertSeller(request,firstname,middlename,lastname,emailid,address,phonenumber,username,password,accountnumber,routingnumber);
				if (msg.equals("success")) {
					mav.addObject("msg","You are successfully registered");
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
	
