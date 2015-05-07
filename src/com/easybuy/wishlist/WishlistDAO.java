package com.easybuy.wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.product.domain.Product;
import com.easybuy.wishlist.domain.WishlistItem;

@Repository("wishlist:wishlistDAO")
public class WishlistDAO {

	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Product> getByUsername(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("wishlist.selectCountByUsername",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Product>) sqlSessionTemplate.selectList("wishlist.selectByUsername", props);
	}
	
	public void insertItem(WishlistItem wishlistItem){
		sqlSessionTemplate.insert("wishlist.insertItem",wishlistItem);
	}
	
	public void deleteItem(WishlistItem wishlistItem) {
		sqlSessionTemplate.delete("wishlist.deleteItem", wishlistItem);
	}
	
	public void deleteProduct(long product_id) {
		sqlSessionTemplate.delete("wishlist.deleteProduct", product_id);
	}
}
