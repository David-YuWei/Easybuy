package com.easybuy.logout;


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

@Controller("logoutController")
@RequestMapping("/logout")
public class LogoutController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public LogoutController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotoLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			String type = request.getParameter("type");
			if(type !=null && type.equals("1")){
				mav.addObject("msg", "Your account has been closed, and you are successfully logged out.");
			}
			else{
				mav.addObject("msg", "You are successfully logged out.");
			}
			userService.logout(request);
			mav.setViewName("/logout");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
}
