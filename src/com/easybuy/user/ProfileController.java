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
import com.easybuy.user.domain.Admin;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;
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
			@RequestParam(value = "user_name", required = false) String username) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
				Buyer buyer = userService.getBuyerById(username);
				mav.addObject("buyerInfo", buyer);
				mav.setViewName("/user/myProfile_buyer");
				return mav;
			}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/seller", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sellerProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_name", required = false) String username) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			Seller seller = userService.getSellerById(username);
			mav.addObject("sellerInfo", seller);
			
				mav.setViewName("/user/myProfile_seller");
				return mav;
			
				
			}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	@RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView adminProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_name", required = false) String username)throws ServletException {
				ModelAndView mav = new ModelAndView();
				try {
					Admin admin = userService.getAdminById(username);
					mav.addObject("adminInfo", admin);
					mav.setViewName("/user/myProfile_admin");
					return mav;
				}
			 catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			return mav;
		}
}

