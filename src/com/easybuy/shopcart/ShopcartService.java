package com.easybuy.shopcart;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybuy.common.Pager;
import com.easybuy.order.OrderService;
import com.easybuy.order.domain.Order;
import com.easybuy.order.domain.OrderItem;
import com.easybuy.shopcart.domain.ShopcartItem;
import com.easybuy.user.UserDAO;
import com.easybuy.wishlist.domain.WishlistItem;

@Service("shopcart:shopcartService")
public class ShopcartService {

	@Resource(name = "shopcart:shopcartDAO")
	private ShopcartDAO shopcartDAO;
	
	@Resource(name = "order:orderService")
	private OrderService orderService;
	
	public ShopcartService(){
		
	}
	
	public List<ShopcartItem> getList(Pager pager,String user_name){
		return shopcartDAO.getList(pager,user_name);
	}
	
	public boolean updateQuantity(ShopcartItem si){
		try{
			shopcartDAO.updateQuantity(si);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean insertItem(ShopcartItem shopcartItem){
		try{
			shopcartDAO.insertItem(shopcartItem);
			return true;
		}
		catch(Exception e){
			return true;
		}
	}
	
	public boolean deleteItem(ShopcartItem shopcartItem){
		try{
			shopcartDAO.deleteItem(shopcartItem);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean placeOrder(Order order) throws Exception{
		Pager pager = new Pager(1, 20);
		List<ShopcartItem> items = getList(pager,order.getUser_name());
		List<OrderItem> orderItems = new LinkedList<OrderItem>();
		//deleteItems first 20 items in shopcart
		List<Long> product_ids = new LinkedList<Long>();
		for(ShopcartItem si:items){
			product_ids.add(si.getProduct_id());
		}
		shopcartDAO.deleteItems(order.getUser_name(),product_ids);
		//insert a new order
		orderService.insert(order);
		//insert all the order items
		for(ShopcartItem si:items){
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder_id(order.getOrder_id());
			orderItem.setProduct_id(si.getProduct_id());
			orderItem.setQuantity(si.getQuantity());
			orderItem.setStatus("1");
			orderItems.add(orderItem);
		}
		orderService.insertItems(orderItems);
		return true;
	}
	
	private void deleteItems(String user_name,List<Long> product_ids){
		shopcartDAO.deleteItems(user_name,product_ids);
	}
	
	
}
