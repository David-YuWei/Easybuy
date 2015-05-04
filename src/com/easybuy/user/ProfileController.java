package com.easybuy.user;

import java.util.LinkedHashMap;
import java.util.Map;

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
import com.easybuy.user.domain.User;
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
	
	@RequestMapping(value = "/updatebuyer", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updatebuyer(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		ModelAndView mav = new ModelAndView();
		Buyer user = (Buyer)userService.getUser(request);
		try {
		String firstname= request.getParameter("firstname");
		String middlename= request.getParameter("middlename");
		String lastname= request.getParameter("lastname");
		String emailid= request.getParameter("emailid");
		String address= request.getParameter("address");
		String phonenumber= request.getParameter("phonenumber");
		String username= user.getUser_name();
		user.setFirst_name(firstname);
		user.setMiddle_name(middlename);
		user.setLast_name(lastname);
		user.setEmail_id(emailid);
		user.setAddress(address);
		user.setPhone_number(phonenumber);
		String msg = userService.updateBuyer(request,user);
			if (msg.equals("success")) {
				mav.addObject("msg","<font style=\"color:green;\">Profile was successfully updated</font>");
				mav.addObject("buyerInfo", user);
				mav.setViewName("/user/myProfile_buyer");
				return mav;
			}
			else{
				mav.addObject("msg","save failed.");
				mav.addObject("buyerInfo", user);
				mav.setViewName("/user/myProfile_buyer");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/updateseller", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateseller(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		ModelAndView mav = new ModelAndView();
		Seller user = (Seller)userService.getUser(request);
		try {
		String firstname= request.getParameter("firstname");
		String middlename= request.getParameter("middlename");
		String lastname= request.getParameter("lastname");
		String emailid= request.getParameter("emailid");
		String address= request.getParameter("address");
		String phonenumber= request.getParameter("phonenumber");
		user.setFirst_name(firstname);
		user.setMiddle_name(middlename);
		user.setLast_name(lastname);
		user.setEmail_id(emailid);
		user.setAddress(address);
		user.setPhone_number(phonenumber);
		String msg = userService.updateSeller(request,user);
			if (msg.equals("success")) {
				mav.addObject("msg","<font style=\"color:green;\">Profile was successfully updated</font>");
				mav.addObject("sellerInfo", user);
				mav.setViewName("/user/myProfile_seller");
				return mav;
			}
			else{
				mav.addObject("msg","save failed.");
				mav.addObject("sellerInfo", user);
				mav.setViewName("/user/myProfile_seller");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/buyer", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView buyerProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_name", required = true) String username) throws ServletException {
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
			@RequestParam(value = "user_name", required = true) String username) throws ServletException {
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
			@RequestParam(value = "user_name", required = true) String username)throws ServletException {
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
	@RequestMapping(value = "/deletebuyer", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delete_user(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user_name", required = true) String username) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			System.out.println("a");
			userService.deleteBuyer(username);
			model.put("status", "success");
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
}

