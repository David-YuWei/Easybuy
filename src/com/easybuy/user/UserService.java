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
import com.easybuy.user.domain.User;

@Service("user:userService")
public class UserService {

	private static final String REQUEST_ATTR_USERNAME = "user";
	@Resource(name = "user:userDAO")
	private UserDAO userDAO;
	
	public UserService() {
	}
	
	public HttpSession getSession(HttpServletRequest request) throws Exception {
		return request.getSession();
	}
	
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User)session.getAttribute(REQUEST_ATTR_USERNAME);
	}
	
	public String checkUser(HttpServletRequest request, String username, String password){
		HttpSession session = request.getSession();
		session.invalidate();
		//check user in database
		String result = userDAO.checkExistByUsernameAndPassword(username, password);
		if(result !=null){
			session = request.getSession();
			User user = null;
			if(result.equals("buyer")){
				user = userDAO.getBuyerById(username);
			}
			else if(result.equals("seller")){
				user = userDAO.getSellerById(username);
			}
			else{
				user = userDAO.getAdminById(username);
			}
			session.setAttribute("user", user);
			return "success";
		}
		else{
			return "incorrect username or password";
		}
	}
	
	public Buyer getBuyerById(String username){
		Buyer buyer = userDAO.getBuyerById(username);
		return buyer;
	}
	
	public Seller getSellerById(String username){
		Seller seller = userDAO.getSellerById(username);
		return seller;
	}
	
	public Admin getAdminById(String username){
		Admin admin = userDAO.getAdminById(username);
		return admin;
	}
	
	public List<Buyer> getBuyerList(Pager pager){
		List<Buyer> buyers= userDAO.getBuyerList(pager);
		if(buyers !=null){
			for(Buyer br:buyers){
				//translates value of the attribute to practical meaning
			}
		}
		return buyers;
	}
	
	public List<Seller> getSellerList(Pager pager){
		List<Seller> sellers= userDAO.getSellerList(pager);
		if(sellers !=null){
			for(Seller br:sellers){
				//translates value of the attribute to practical meaning
			}
		}
		return sellers;
	}

	public void insertBuyer(Buyer buyer) throws Exception{
		
	}

	public void deleteBuyer(String id) throws Exception{
		userDAO.deleteBuyer(id);
	}
	
	public void approveSeller(String id) throws Exception{
		userDAO.approveSeller(id);
	}
	
	public void declineSeller(String id) throws Exception{
		userDAO.declineSeller(id);
	}
	
	public void updateBuyer(Buyer buyer) throws Exception{
		
	}
	
}
