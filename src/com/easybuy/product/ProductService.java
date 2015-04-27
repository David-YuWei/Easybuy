package com.easybuy.product;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easybuy.common.Pager;
import com.easybuy.product.domain.Product;
import com.easybuy.product.domain.Review;
import com.easybuy.user.UserDAO;

@Service("product:productService")
public class ProductService {

	
	@Resource(name = "product:productDAO")
	private ProductDAO productDAO;
	
	public List<String> getAllBrands(){
		//todo.... local caching
		return productDAO.getAllBrands();
	}
	
	public Product getById(long product_id){
		return productDAO.getById(product_id);
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
	
}
