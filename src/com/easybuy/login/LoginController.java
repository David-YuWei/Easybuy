package com.easybuy.login;


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

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public LoginController(){
		
	}

	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView gotoLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/login");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/signin", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "url", required = false) String url) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				mav.addObject("msg", "Plase enter your Username or password");
				mav.addObject("url", url);
				mav.setViewName("/login");
				return mav;
			} else {
				String msg = userService.checkUser(request,username,password);
				if (msg.equals("success")) {
					mav.setViewName("redirect:/");
				} else {
					mav.addObject("url", url);
					mav.addObject("msg", "Invalid Username and password");
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
