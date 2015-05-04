package com.easybuy.shopcart;

import java.util.Date;
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
import com.easybuy.order.domain.Order;
import com.easybuy.product.domain.Product;
import com.easybuy.shopcart.domain.ShopcartItem;
import com.easybuy.user.UserService;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.User;
import com.easybuy.wishlist.domain.WishlistItem;

@Controller("shopcartController")
@RequestMapping("/shopcart")
public class ShopcartController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	@Resource(name = "shopcart:shopcartService")
	private ShopcartService shopcartService;
	
	public ShopcartController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toshopcart(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/shopcart/shopcart");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public ModelAndView shopcartList(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(1, 20);
			User user = userService.getUser(request);
			List<ShopcartItem> items = shopcartService.getList(pager,user.getUser_name());
			float subtotal = 0;
			if(items !=null){
				for(ShopcartItem si:items){
					subtotal += si.getPrice() * si.getQuantity();
				}
			}

			model.put("status", "success");
			model.put("pager", pager);
			model.put("subtotal", subtotal);
			model.put("list", items);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/deleteItem", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			ShopcartItem si = new ShopcartItem();
			si.setUser_name_buyer(user.getUser_name());
			si.setProduct_id(product_id);
			boolean result = shopcartService.deleteItem(si);
			if(result){
				model.put("status", "success");
			}
			else{
				model.put("status", "error");
			}
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/updateQuantity", method = {RequestMethod.GET})
	public ModelAndView updateQuantity(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id,
			@RequestParam(value = "quantity", required = true) int quantity) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			ShopcartItem si = new ShopcartItem();
			si.setUser_name_buyer(user.getUser_name());
			si.setProduct_id(product_id);
			si.setQuantity(quantity);
			boolean result = shopcartService.updateQuantity(si);
			if(result){
				model.put("status", "success");
			}
			else{
				model.put("status", "error");
			}
			model.put("status", "success");
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/checkout", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tocheckout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/shopcart/placeOrder");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/placeorder", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView placeporder(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Order order = new Order();
		try {
			String full_name = request.getParameter("full_name");
			String address_1 = request.getParameter("address_1");
			String address_2 = request.getParameter("address_2");
			String phone = request.getParameter("phone");
			String option = request.getParameter("options");
			String paypal_account = request.getParameter("paypal_account");
			String passcode = request.getParameter("passcode");
			String price = request.getParameter("price");
			String tax = request.getParameter("tax");
			order.setFull_name(full_name);
			order.setAddress_1(address_1);
			order.setAddress_2(address_2);
			order.setPhone(phone);
			order.setShipping_options(option);
			order.setPaypal_account(paypal_account);
			order.setPasscode(passcode);
			if (StringUtils.isBlank(full_name) || StringUtils.isBlank(address_1) 
					|| StringUtils.isBlank(phone) || StringUtils.isBlank(option) || StringUtils.isBlank(paypal_account)
					|| StringUtils.isBlank(passcode) ) {
				mav.addObject("message", "input should not be empty");
				mav.addObject("order", order);
				mav.setViewName("/shopcart/placeOrder");
				return mav;
			}
			order.setPrice(Float.parseFloat(price));
			order.setTax(Float.parseFloat(tax));
			
			User user = userService.getUser(request);
			order.setUser_name(user.getUser_name());
			order.setOrder_time(new Date());
			order.setStatus("1");
			boolean result = shopcartService.placeOrder(order);
			if(result){
				mav.setViewName("/order/orderList");
			}
			else{
				mav.addObject("order", order);
				mav.addObject("message", "save failed");
				mav.setViewName("/shopcart/placeOrder");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("order", order);
			mav.addObject("message", "save failed");
			mav.setViewName("/shopcart/placeOrder");
		} finally {
		}
		return mav;
	}
}
