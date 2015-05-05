package com.easybuy.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.product.domain.Product;
import com.easybuy.product.domain.Review;
import com.easybuy.user.domain.Buyer;

@Repository("product:productDAO")
public class ProductDAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<String> getAllBrands(){
		return (List<String>) sqlSessionTemplate.selectList("product.selectAllBrands");
	}
	
	public Product getById(long product_id){
		return (Product) sqlSessionTemplate.selectOne("product.selectById",product_id);
	}
	
	public Review getReview(String user_name,long product_id){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		props.put("product_id", product_id);
		return (Review) sqlSessionTemplate.selectOne("product.selectReview",props);
	}
	
	public List<Product> getHotProductList(){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("firstResult", 0);
		props.put("maxResult", 10);
		return (List<Product>) sqlSessionTemplate.selectList("product.selectHotProductList", props);
	}
	
	public List<Product> searchByDefault(Pager pager){
		Map<String, Object> props = new HashMap<String, Object>();
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectCountByDefault",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("product.selectByDefault", props);
	}
	
	public List<Product> searchByBrandAndName(Pager pager,String brand_name,String content){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("brand_name", brand_name);
		props.put("product_name", "%"+content+"%");
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectCountByBrandAndName",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("product.selectByBrandAndName", props);
	}
	
	public List<Product> searchByBrand(Pager pager,String brand_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("brand_name", brand_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectCountByBrand",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("product.selectByBrand", props);
	}
	
	public List<Product> searchByBrandOrName(Pager pager,String content){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("brand_name", "%"+content+"%");
		props.put("product_name", "%"+content+"%");
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectCountByBrandOrName",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("product.selectByBrandOrName", props);
	}
	
	public List<Product> getSellerProducts(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectSellerProductsCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("product.selectSellerProducts", props);
	}
	
	public int getReviewsCount(long product_id){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("product_id", product_id);
		return (Integer) sqlSessionTemplate.selectOne("product.selectReviewsCount",props);	
	}
	
	public List<Review> getReviews(Pager pager,long product_id){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("product_id", product_id);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("product.selectReviewsCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Review>) sqlSessionTemplate.selectList("product.selectReviews", props);
	}
	
	public void insert(Product product){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("brand_name", product.getBrand_name());
		try{
			sqlSessionTemplate.insert("product.insert_brand",props);
		}
		catch(Exception E){
		}
		sqlSessionTemplate.insert("product.insert",product);
	}
	
	public void insertBrand(String brand_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("brand_name", brand_name);
		try{
			sqlSessionTemplate.insert("product.insert_brand",props);
		}
		catch(Exception E){
		}
	}
	
	public void insertReview(Review review){
		sqlSessionTemplate.insert("product.insertReview",review);
	}
	
	public void updateReview(Review review){
		sqlSessionTemplate.update("product.updateReview",review);
	}
	
	public void updateRanking(float ranking,long product_id){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("ranking", ranking);
		props.put("product_id", product_id);
		sqlSessionTemplate.update("product.updateRanking", props);
	}
	
	public void update(Product product) {
		if(product.getImage() == null || product.getImage().equals("")){
			sqlSessionTemplate.update("product.updateWithoutImage", product);
		}
		else{
			sqlSessionTemplate.update("product.updateWithImage", product);
		}
	}

	public void deleteById(long product_id) {
		sqlSessionTemplate.delete("product.deleteById", product_id);
	}
	
	public void deleteBySellerName(String username){
		sqlSessionTemplate.delete("product.deleteBySellerName", username);
	}
	
}
