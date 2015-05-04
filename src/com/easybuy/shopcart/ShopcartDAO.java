package com.easybuy.shopcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.shopcart.domain.ShopcartItem;
import com.easybuy.user.domain.Buyer;
import com.easybuy.wishlist.domain.WishlistItem;

@Repository("shopcart:shopcartDAO")
public class ShopcartDAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<ShopcartItem> getList(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("shopcart.selectListCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<ShopcartItem>) sqlSessionTemplate.selectList("shopcart.selectList", props);
	}
	
	public void insertItem(ShopcartItem shopcartItem){
		sqlSessionTemplate.insert("shopcart.insertItem",shopcartItem);
	}
	
	public void updateQuantity(ShopcartItem shopcartItem){
		sqlSessionTemplate.update("shopcart.updateQuantity",shopcartItem);
	}
	
	public void deleteItem(ShopcartItem shopcartItem) {
		sqlSessionTemplate.delete("shopcart.deleteItem", shopcartItem);
	}
	
	public void deleteItems(String user_name,List<Long> product_ids){
		Map<String, Object> params = new HashMap<String, Object>();  
	    params.put("product_ids", product_ids);  
	    params.put("user_name", user_name);
		sqlSessionTemplate.delete("shopcart.deleteItems", params);
	}
}
