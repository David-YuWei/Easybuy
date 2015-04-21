package com.easybuy.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.user.domain.Buyer;


@Repository("user:buyerDAO")
public class BuyerDAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public BuyerDAO() {
	}

//	public void insert(Buyer buyer) throws Exception {
//		sqlSessionTemplate.insert("user.buyer.insert", buyer);
//	}
//
//	public void update(Buyer buyer) throws Exception {
//		sqlSessionTemplate.update("user.buyer.update", buyer);
//	}
	
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
	
	public boolean checkExistByUsernameAndPassword(String username, String password){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);
		props.put("password", password);
		Integer total = (Integer) sqlSessionTemplate.selectOne("user.buyer.selectCountByUsernameAndPassword", props);
		if (total == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void delete(String id) {
		sqlSessionTemplate.delete("user.buyer.delete", id);
	}
	
}
