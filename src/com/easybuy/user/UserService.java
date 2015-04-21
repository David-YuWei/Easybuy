package com.easybuy.user;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.easybuy.common.Pager;
import com.easybuy.user.domain.Admin;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;

@Service("user:userService")
public class UserService {

	private static final String REQUEST_ATTR_USERNAME = "user";
	@Resource(name = "user:buyerDAO")
	private BuyerDAO buyerDAO;
	
	public UserService() {
	}
	
	public HttpSession getSession(HttpServletRequest request) throws Exception {
		return request.getSession();
	}
	
	public Object getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(REQUEST_ATTR_USERNAME);
	}
	
	public String checkUser(HttpServletRequest request, String username, String password){
		HttpSession session = request.getSession();
		session.invalidate();
		//check user in database
		boolean result = buyerDAO.checkExistByUsernameAndPassword(username, password);
		if(result){
			session = request.getSession();
			Buyer buyer = new Buyer();
			buyer.setUser_name(username);
			//todo
			session.setAttribute("user", buyer);
			return "success";
		}
		else{
			return "incorrect username or password";
		}
	}
	
	public List<Buyer> getBuyerList(Pager pager){
		List<Buyer> buyers= buyerDAO.getBuyerList(pager);
		if(buyers !=null){
			for(Buyer br:buyers){
				//translates value of the attribute to practical meaning
			}
		}
		return buyers;
	}

	public void insertBuyer(Buyer buyer) throws Exception{
		
	}

	public void deleteBuyer(String id) throws Exception{
		
	}
	
	public void updateBuyer(Buyer buyer) throws Exception{
		
	}
	
	public List<Buyer> searchBuyerList(){
		return null;
	}
	
	public Buyer searchBuyerById(String id){
		return null;
	}
	
	
	public void deleteSeller(long id) throws Exception{
		
	}
	
}
