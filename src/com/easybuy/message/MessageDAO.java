package com.easybuy.message;

import com.easybuy.message.domain.Message;
import com.easybuy.product.domain.Product;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.*;
import com.easybuy.user.UserService.*;
import com.easybuy.user.domain.User;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;

@Repository("message:messageDAO")
public class MessageDAO {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Message getById(int message_id){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("message_id", message_id);
		return (Message)sqlSessionTemplate.selectOne("message.selectById",props);
	}
	
	public List<Message> getReceivedList(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("message.selectReceivedCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Message>) sqlSessionTemplate.selectList("message.selectReceivedList", props);
	}
	
	public List<Message> getSentList(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("message.selectSentCount",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Message>) sqlSessionTemplate.selectList("message.selectSentList", props);
	}
	
	public void insert(Message message){
		{
			sqlSessionTemplate.insert("message.insert",message);
		}
		
	}
	
	public void sendNotif(Message message){
		sqlSessionTemplate.insert("message.insert",message);
	}
	
	
	public String checkTouser(String touser){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("touser", touser);
		return (String)sqlSessionTemplate.selectOne("message.checkTouser",props);
	}
	
	
	
	
	
	
	
}
