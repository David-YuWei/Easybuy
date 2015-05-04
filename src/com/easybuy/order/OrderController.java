package com.easybuy.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.common.Pager;
import com.easybuy.order.domain.Order;
import com.easybuy.order.domain.OrderItem;
import com.easybuy.product.domain.Product;
import com.easybuy.shopcart.ShopcartService;
import com.easybuy.shopcart.domain.ShopcartItem;
import com.easybuy.user.UserService;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;
import com.easybuy.user.domain.User;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	@Resource(name = "order:orderService")
	private OrderService orderService;
	
	public OrderController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toorder(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/order/orderList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public ModelAndView orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(1, 20);
			User user = userService.getUser(request);
			List<Order> items = null;
			if(user instanceof Buyer){
				items = orderService.getListAsBuyer(pager, user.getUser_name());
			}
			else if(user instanceof Seller){
				items = orderService.getListAsSeller(pager, user.getUser_name());
			}
			else{
				items = orderService.getListAsAdmin(pager);
			}
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", items);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/{order_id:\\d+}/edit", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("order_id") long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			Order order = orderService.getById(order_id);
			mav.addObject("order", order);
			if(order.getStatus().equals("2")){
				mav.addObject("operateType","view");
			}
			else{
				mav.addObject("operateType","edit");
			}
			mav.setViewName("/order/order");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/{order_id:\\d+}/updateInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateInfo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("order_id") long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Order order = new Order();
		try {
			order = orderService.getById(order_id);
			String full_name = request.getParameter("full_name");
			String address_1 = request.getParameter("address_1");
			String address_2 = request.getParameter("address_2");
			String phone = request.getParameter("phone");
			String option = request.getParameter("options");
			order.setFull_name(full_name);
			order.setAddress_1(address_1);
			order.setAddress_2(address_2);
			order.setPhone(phone);
			order.setShipping_options(option);
			if (StringUtils.isBlank(full_name) || StringUtils.isBlank(address_1) 
					|| StringUtils.isBlank(phone) || StringUtils.isBlank(option) ) {
				mav.addObject("message", "input should not be empty");
				mav.addObject("operateType","edit");
				mav.addObject("order", order);
				mav.setViewName("/order/order");
				return mav;
			}
			float price = 0;
			float tax = 0;
			for(OrderItem item:order.getItems()){
				String quantity = request.getParameter("quantity_"+item.getProduct_id());
				if(quantity ==null || quantity.trim().equals("")){
					quantity = "1";
				}
				item.setQuantity(Integer.parseInt(quantity));
				price += item.getPrice() * Integer.parseInt(quantity);
			}
			order.setPrice(price);
			tax = (float)(price * 0.0824);
			BigDecimal bg = new BigDecimal(tax);
	        tax = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			order.setTax(tax);
			
			boolean result = orderService.updateInfo(order);
			if(result){
				mav.setViewName("/order/orderList");
			}
			else{
				mav.addObject("order", order);
				mav.addObject("operateType","edit");
				mav.addObject("message", "save failed");
				mav.setViewName("/order/order");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("order", order);
			mav.addObject("operateType","edit");
			mav.addObject("message", "save failed");
			mav.setViewName("/order/order");
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/{order_id:\\d+}/view", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("order_id") long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		User user = userService.getUser(request);
		try {
			Order order = orderService.getById(order_id);
			if(user instanceof Seller){
				Order temp = order.clone();
				Iterator<OrderItem> it = temp.getItems().iterator();
				while(it.hasNext()){
					OrderItem item = it.next();
					if(!item.getUser_name().equals(user.getUser_name())){
						it.remove();
					}
				}
				order = temp;
			}
			mav.addObject("order", order);
			mav.addObject("operateType","view");
			mav.setViewName("/order/order");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/shipItem", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView shipItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id,
			@RequestParam(value = "order_id", required = true) long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			OrderItem item = new OrderItem();
			item.setOrder_id(order_id);
			item.setProduct_id(product_id);
			item.setStatus("2");
			boolean result = orderService.shipItem(item);
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
	
	@RequestMapping(value = "/deleteItem", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id,
			@RequestParam(value = "order_id", required = true) long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			OrderItem item = new OrderItem();
			item.setOrder_id(order_id);
			item.setProduct_id(product_id);
			boolean result = orderService.deleteItem(item);
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
	
	@RequestMapping(value = "/deleteOrder", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "order_id", required = true) long order_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			boolean result = orderService.deleteOrder(order_id);
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
	
}
