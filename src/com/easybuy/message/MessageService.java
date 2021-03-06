package com.easybuy.message;
import com.easybuy.message.domain.Message;

import javax.annotation.Resource;

import java.util.List;

import com.easybuy.product.ProductDAO;
import com.easybuy.product.domain.Product;
import com.easybuy.user.*;
import com.easybuy.user.UserService.*;
import com.easybuy.user.domain.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

import com.easybuy.common.Pager;

@Service("message:messageService")
public class MessageService {
	
	@Resource(name = "message:messageDAO")
	private MessageDAO messageDAO;
	
	public List<Message> getReceivedList(Pager pager,String user_name){
		return messageDAO.getReceivedList(pager,user_name);
	}
	
	
	public Message getById(int message_id){
		return messageDAO.getById(message_id);
	}
	
	public List<Message> getSentList(Pager pager,String user_name){
		return messageDAO.getSentList(pager,user_name);
	}
	
	public boolean insert(Message message){
		try{
			messageDAO.insert(message);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public String selectSentCount(String user_name){
		String empty="";
		int count=0;
		count=messageDAO.selectSentCount(user_name);
		if(count==0){
			empty="Sent list is empty";
		}
		return empty;
	}
	
	public boolean sendRegistrationNotif(String touser){
		try{
			Message message =new Message();
			message.setTouser(touser);
			message.setType("0");
			message.setFromuser("Easybuy");
			message.setContent("Hello "+touser+", your account has been approved. Welcome to Easybuy!");
			messageDAO.sendNotif(message);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean sendOrderNotif(String touser, long orderId){
		try{
			Message message =new Message();
			message.setTouser(touser);
			message.setType("1");
			message.setFromuser("Easybuy");
			message.setContent("Hello "+touser+", you have a new order: "+orderId+" .");
			messageDAO.sendNotif(message);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean sendOrderUpdateNotif(String touser, long orderId){
		try{
			Message message =new Message();
			message.setTouser(touser);
			message.setType("2");
			message.setFromuser("Easybuy");
			message.setContent("Hello "+touser+", the order: "+orderId+" has been updated.");
			messageDAO.sendNotif(message);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean sendReviewNotif(String touser, String productName){
		try{
			Message message =new Message();
			message.setTouser(touser);
			message.setType("3");
			message.setFromuser("Easybuy");
			message.setContent("Hello "+touser+", you have a new review for your product "+productName+".");
			messageDAO.sendNotif(message);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean checkTouser(String touser){
		try{
			String test;
			test=messageDAO.checkTouser(touser);
			if(test!=null){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	

}
