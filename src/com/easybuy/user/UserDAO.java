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
	
	
	public String checkExistByUsername(String username)
	{
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);	
		
		List<String> result = (List<String>) sqlSessionTemplate.selectList("user.selectByUsername",props);
		if (result ==null || result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	public int insertBuyer(String firstname,String middlename,String lastname,String emailid,String address,String phonenumber,String username,String password)
	{
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);
		props.put("firstname", firstname);
		props.put("lastname", lastname);
		props.put("middlename", middlename);
		props.put("emailid", emailid);
		props.put("address", address);
		props.put("phonenumber", phonenumber);
		props.put("password", password);
		
		return sqlSessionTemplate.insert("user.buyer.insertBuyer",props);
	}
	

	public int insertSeller(String firstname,String middlename,String lastname,String emailid
			,String address,String phonenumber,String username,String password,String accountnumber,String routingnumber)
	{
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);
		props.put("firstname", firstname);
		props.put("lastname", lastname);
		props.put("middlename", middlename);
		props.put("emailid", emailid);
		props.put("address", address);
		props.put("phonenumber", phonenumber);
		props.put("password", password);
		props.put("accountnumber", accountnumber);
		props.put("routingnumber", routingnumber);
		

		return sqlSessionTemplate.insert("user.seller.insertSeller",props);
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
	
	public String viewBuyer(String username){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", username);
		
		List<UserType> result = (List<UserType>) sqlSessionTemplate.selectList("user.selectCountByUsernameAndPassword", props);
		if (result ==null || result.size() == 0) {
			return null;
		} else {
			return result.get(0).getType();
		}
	}

	public int updateBuyer(Buyer buyer)
	{
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", buyer.getUser_name());
		props.put("firstname", buyer.getFirst_name());
		props.put("lastname", buyer.getLast_name());
		props.put("middlename", buyer.getMiddle_name());
		props.put("emailid", buyer.getEmail_id());
		props.put("address", buyer.getAddress());
		props.put("phone_number", buyer.getPhone_number());
		
		return sqlSessionTemplate.update("user.buyer.update_buyer",props);
	}
	
	public int updateSeller(Seller seller)
	{
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("username", seller.getUser_name());
		props.put("firstname", seller.getFirst_name());
		props.put("lastname", seller.getLast_name());
		props.put("middlename", seller.getMiddle_name());
		props.put("emailid", seller.getEmail_id());
		props.put("address", seller.getAddress());
		props.put("phone_number", seller.getPhone_number());
		
		return sqlSessionTemplate.update("user.seller.update_seller",props);
	}
	
	
	public void deleteBuyer(String id) {
		sqlSessionTemplate.update("user.buyer.delete", id);
	}
	public void deleteBuyer_user(String id) {
		sqlSessionTemplate.update("user.buyer.delete_user", id);
	}
	
	public void approveSeller(String id) {
		sqlSessionTemplate.update("user.seller.approve", id);
	}
	public void declineSeller(String id) {
		sqlSessionTemplate.update("user.seller.decline", id);
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
