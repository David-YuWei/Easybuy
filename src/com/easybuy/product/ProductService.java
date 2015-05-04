package com.easybuy.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybuy.common.Pager;
import com.easybuy.message.MessageService;
import com.easybuy.product.domain.Product;
import com.easybuy.product.domain.Review;
import com.easybuy.user.UserDAO;

@Service("product:productService")
public class ProductService {

	
	@Resource(name = "product:productDAO")
	private ProductDAO productDAO;
	
	@Resource(name = "message:messageService")
	private MessageService messageService;
	
	
	public List<String> getAllBrands(){
		//todo.... local caching
		return productDAO.getAllBrands();
	}
	
	public Product getById(long product_id){
		return productDAO.getById(product_id);
	}
	
	public Review getReview(String user_name,long product_id){
		return productDAO.getReview(user_name, product_id);
	}
	
	public List<Product> search(Pager pager,String brand_name,String content){
		if(brand_name ==null || brand_name.trim().equals("")){
			if(content ==null || content.trim().equals("")){
				return productDAO.searchByDefault(pager);
			}
			else{
				return productDAO.searchByBrandOrName(pager,content);
			}
		}
		else{
			if(content ==null || content.trim().equals("")){
				return productDAO.searchByBrand(pager,brand_name);
			}
			else{
				return productDAO.searchByBrandAndName(pager,brand_name,content);
			}
		}
	}
	
	public List<Product> searchHot(){
		return productDAO.getHotProductList();
	}
	
	public List<Product> getSellerProducts(Pager pager,String user_name){
		return productDAO.getSellerProducts(pager,user_name);
	}
	
	public List<Review> getReviews(Pager pager,long product_id){
		return productDAO.getReviews(pager,product_id);
	}
	
	public boolean insert(Product product){
		try{
			productDAO.insert(product);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean insertReview(Review review) throws Exception{
		productDAO.insertReview(review);
		Product product = getById(review.getProduct_id());
		//calculate ranking
		int reviewCount = productDAO.getReviewsCount(review.getProduct_id());
		float ranking = (product.getRanking() * reviewCount + review.getRanking()) / (reviewCount + 1);
		BigDecimal bg = new BigDecimal(ranking);
        ranking = bg.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		productDAO.updateRanking(ranking, review.getProduct_id());
		messageService.sendReviewNotif(product.getUser_name(), product.getProduct_name());
		return true;
	}
	
	public boolean updateReview(Review review){
		try{
			productDAO.updateReview(review);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean update(Product product){
		try{
			productDAO.update(product);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteById(long product_id){
		try{
			productDAO.deleteById(product_id);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	public boolean deleteBySellerName(String username){
		try{
			productDAO.deleteBySellerName(username);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
}
