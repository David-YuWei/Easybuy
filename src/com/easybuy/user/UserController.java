package com.easybuy.user;

import java.util.LinkedHashMap;
import java.util.List;
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

import com.easybuy.common.Pager;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;


@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public UserController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toUserList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("msg", "1234");
			mav.setViewName("/user/userList_seller");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/buyer", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tobuyer(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			
			mav.setViewName("/user/userList_buyer");
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return mav;
		}
	}
	
	@RequestMapping(value = "/seller", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toseller(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			
			mav.setViewName("/user/userList_seller");
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return mav;
		}
	}

	@RequestMapping(value = "/buyer/list", method = {RequestMethod.GET})
	public ModelAndView buyerList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Buyer> list = userService.getBuyerList(pager);
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", list);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/seller/list", method = {RequestMethod.GET})
	public ModelAndView sellerList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Seller> list = userService.getSellerList(pager);
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", list);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
}
