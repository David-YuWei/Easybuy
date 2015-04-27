package com.easybuy.product;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.easybuy.common.ImageService;
import com.easybuy.common.Pager;
import com.easybuy.product.domain.Product;
import com.easybuy.product.domain.Review;
import com.easybuy.user.UserService;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.User;
import com.easybuy.wishlist.WishlistService;
import com.easybuy.wishlist.domain.WishlistItem;


@Controller("productController")
@RequestMapping("/product")
public class ProductController {

	@Resource(name = "user:userService")
	private UserService userService;
	@Resource(name = "product:productService")
	private ProductService productService;
	@Resource(name = "imageService")
	private ImageService imageService;
	@Resource(name = "wishlist:wishlistService")
	private WishlistService wishlistService;
	
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tosearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("redirect:/product/search");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "brand_name", required = false, defaultValue = "") String brand_name,
			@RequestParam(value = "content", required = false, defaultValue = "") String content) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			List<String> brands = productService.getAllBrands();
			mav.addObject("brand_name", brand_name);
			mav.addObject("content", content);
			mav.addObject("brand_list", brands);
			mav.setViewName("/product/searchProduct");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/hot", method = {RequestMethod.GET})
	public ModelAndView searchHot(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			List<Product> products = productService.searchHot();
			List<String> brands = productService.getAllBrands();
			model.put("brand_list", brands);
			model.put("status", "success");
			model.put("list", products);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/searchProduct", method = {RequestMethod.GET})
	public ModelAndView searchProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "brand_name", required = true) String brand_name,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Product> products = productService.search(pager,brand_name,content);
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
	
	@RequestMapping(value = "/{productId:\\d+}/view", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("productId") long product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			Product product = productService.getById(product_id);
			List<String> brands = productService.getAllBrands();
			mav.addObject("brand_list", brands);
			mav.addObject("product", product);
			mav.setViewName("/product/product");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/review/list", method = {RequestMethod.GET})
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Review> reviews = productService.getReviews(pager,product_id);
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", reviews);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/new", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tonew(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/product/product_new");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Product product = new Product();
		try {
			String product_name = request.getParameter("product_name");
			String price = request.getParameter("price");
			String brand_name = request.getParameter("brand_name");
			String description = request.getParameter("description");
			product.setProduct_name(product_name);
			product.setBrand_name(brand_name);
			product.setDescription(description);
			product.setPrice(Float.parseFloat(price));
			if (StringUtils.isBlank(product_name) || StringUtils.isBlank(price) 
					|| StringUtils.isBlank(brand_name) || StringUtils.isBlank(description)) {
				mav.addObject("message", "product name/price/brand_name/description should not be empty.");
				mav.addObject("product", product);
				mav.setViewName("/product/product_new");
				return mav;
			}
			if(product_name.length() > 40){
				mav.addObject("message", "product name too long.");
				mav.addObject("product", product);
				mav.setViewName("/product/product_new");
				return mav;
			}
			String image = imageService.upload(request, "image");
			if(image.equals("")){
				mav.addObject("message", "image required.");
				mav.addObject("product", product);
				mav.setViewName("/product/product_new");
				return mav;
			}
			User user = userService.getUser(request);
			
			product.setImage(image);
			product.setUser_name(user.getUser_name());
			product.setRanking(5);
			product.setStatus("1");
			product.setUpdate_time(new Date());
			boolean result = productService.insert(product);
			if(result){
				mav.setViewName("/product/sellerProductList");
			}
			else{
				mav.addObject("product", product);
				mav.addObject("message", "save failed.");
				mav.setViewName("/product/product_new");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("product", product);
			mav.addObject("message", "save failed.");
			mav.setViewName("/product/product_new");
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/sellerProducts", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toSellerProducts(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/product/sellerProductList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/sellerProducts/list", method = {RequestMethod.GET})
	public ModelAndView sellerProductList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Product> products = productService.getSellerProducts(pager,user.getUser_name());
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
	
	@RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id) {
		ModelAndView mav = new ModelAndView();
		try {
			Product product = productService.getById(product_id);
			List<String> brands = productService.getAllBrands();
			mav.addObject("brand_list", brands);
			mav.addObject("product", product);
			mav.setViewName("/product/product_edit");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Product product = new Product();
		try {
			String product_id = request.getParameter("product_id");
			String product_name = request.getParameter("product_name");
			String price = request.getParameter("price");
			String brand_name = request.getParameter("brand_name");
			String description = request.getParameter("description");
			if (StringUtils.isBlank(product_id) || StringUtils.isBlank(product_name) || StringUtils.isBlank(price) 
					|| StringUtils.isBlank(brand_name) || StringUtils.isBlank(description)) {
				mav.addObject("message", "product name/price/brand_name/description should not be empty.");
				mav.setViewName("/product/product_new");
				return mav;
			}
			String image = imageService.upload(request, "image");
			if(!image.equals("")){
				product.setImage(image);
			}

			product.setProduct_id(Long.parseLong(product_id));
			product.setProduct_name(product_name);
			product.setBrand_name(brand_name);
			product.setDescription(description);
			product.setPrice(Float.parseFloat(price));
			
			product.setUpdate_time(new Date());
			boolean result = productService.update(product);
			if(result){
				mav.setViewName("/product/sellerProductList");
			}
			else{
				mav.addObject("product", product);
				mav.addObject("message", "update failed.");
				mav.setViewName("/product/product_edit");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("product", product);
			mav.addObject("message", "update failed.");
			mav.setViewName("/product/product_edit");
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) long product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			boolean result = productService.deleteById(product_id);
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
	
	@RequestMapping(value = "/add2wishlist", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView add2wishlist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "product_id", required = true) String product_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			if(user == null){
				model.put("redirect", "/login");
			}
			else{
				WishlistItem wli = new WishlistItem();
				wli.setUser_name(user.getUser_name());
				wli.setProduct_id(Long.parseLong(product_id));
				boolean result = wishlistService.insertItem(wli);
				if(result){
					model.put("status", "success");
				}
				else{
					model.put("status", "error");
				}
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
