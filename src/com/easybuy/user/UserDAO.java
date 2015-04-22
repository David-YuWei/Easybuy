package com.easybuy.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.user.domain.Admin;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;

@Repository("user:userDAO")
public class UserDAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Buyer getBuyerById(String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		return (Buyer)sqlSessionTemplate.selectOne("user.buyer.selectById",props);
	}
	
	public Seller getSellerById(String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		return (Seller)sqlSessionTemplate.selectOne("user.seller.selectById",props);
	}
	
	public Admin getAdminById(String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		return (Admin)sqlSessionTemplate.selectOne("user.selectAdminById",props);
	}
	
	public List<Buyer> getBuyerList(Pager pager){
		Map<String, Object> props = new HashMap<String, Object>();
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("user.buyer.selectListCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Buyer>) sqlSessionTemplate.selectList("user.buyer.selectList", props);
	}
	
	public List<Seller> getSellerList(Pager pager){
		Map<String, Object> props = new HashMap<String, Object>();
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("user.seller.selectListCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Seller>) sqlSessionTemplate.selectList("user.seller.selectList", props);
	}
	
	public String checkExistByUsernameAndPassword(String username, String password){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);
		props.put("password", password);
		List<UserType> result = (List<UserType>) sqlSessionTemplate.selectList("user.selectCountByUsernameAndPassword", props);
		if (result ==null || result.size() == 0) {
			return null;
		} else {
			return result.get(0).getType();
		}
	}

	public void deleteBuyer(String id) {
		sqlSessionTemplate.delete("user.buyer.delete", id);
	}
	
	public static class UserType {
		private String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
	
}
