package com.easybuy.wishlist;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.common.Pager;
import com.easybuy.product.ProductService;
import com.easybuy.product.domain.Product;
import com.easybuy.product.domain.Review;
import com.easybuy.user.UserService;
import com.easybuy.user.domain.User;
import com.easybuy.wishlist.domain.WishlistItem;



@Controller("wishlistController")
@RequestMapping("/wishlist")
public class WishlistController {

	@Resource(name = "user:userService")
	private UserService userService;
	@Resource(name = "wishlist:wishlistService")
	private WishlistService wishlistService;
	
	public WishlistController(){
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView towishlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/wishlist/wishlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public ModelAndView wishlist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Product> products = wishlistService.getList(pager,user.getUser_name());
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", products);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/addProduct", method = {RequestMethod.GET})
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			WishlistItem wli = new WishlistItem();
			wli.setUser_name(user.getUser_name());
			wli.setProduct_id(product_id);
			boolean result = wishlistService.insertItem(wli);
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
			@RequestParam(value = "product_id", required = true) long product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			WishlistItem wli = new WishlistItem();
			wli.setUser_name(user.getUser_name());
			wli.setProduct_id(product_id);
			boolean result = wishlistService.deleteItem(wli);
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
