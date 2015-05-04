package com.easybuy.order;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybuy.common.CacheService;
import com.easybuy.common.Pager;
import com.easybuy.message.MessageService;
import com.easybuy.order.domain.Order;
import com.easybuy.order.domain.OrderItem;

@Service("order:orderService")
public class OrderService {

	@Resource(name = "order:orderDAO")
	private OrderDAO orderDAO;
	
	@Resource(name = "common:cacheService")
	private CacheService cacheService;
	
	@Resource(name = "message:messageService")
	private MessageService messageService;
	
	public Order getById(long order_id) throws Exception{
		Object obj = cacheService.get(generateKey(order_id));
		if(obj ==null){
			Order order = orderDAO.selectById(order_id);
			if(order !=null){
				float price = 0;
				float tax = 0;
				
				if(order.getShipping_options().equals("1")){
					order.setShippingCost(3);
				}
				else if(order.getShipping_options().equals("2")){
					order.setShippingCost(10);
				}
				else{
					order.setShippingCost(0);
				}
				List<OrderItem> items = orderDAO.selectItemsById(order_id);
				if(items !=null){
					for(OrderItem item:items){
						order.addItem(item);
						price += item.getPrice() * item.getQuantity();
					}
					tax = (float)(price * 0.0824);
					BigDecimal bg = new BigDecimal(tax);
			        tax = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				}
				
				order.setPrice(price);
				order.setTax(tax);
				cacheService.add(generateKey(order_id), order);
			}
			return order;
		}
		else{
			return (Order) obj;
		}
	}
	
	public List<Order> getListAsBuyer(Pager pager,String user_name) throws Exception{
		List<Order> orders = orderDAO.getListAsBuyer(pager, user_name);
		List<Order> result = new LinkedList<Order>();
		if(orders !=null){
			for(Order order:orders){
				result.add(getById(order.getOrder_id()));
			}
		}
		return result;
	}
	
	public List<Order> getListAsSeller(Pager pager,String user_name) throws Exception{
		List<Order> orders = orderDAO.getListAsSeller(pager, user_name);
		List<Order> result = new LinkedList<Order>();
		if(orders !=null){
			for(Order order:orders){
				Order temp = getById(order.getOrder_id()).clone();
				List<OrderItem> items = new LinkedList<OrderItem>();
				for(OrderItem item:temp.getItems()){
					if(item.getUser_name().equals(user_name)){
						items.add(item);
					}
				}
				temp.setItems(items);
				result.add(temp);
			}
		}
		return result;
	}
	
	public List<Order> getListAsAdmin(Pager pager) throws Exception{
		List<Order> orders = orderDAO.getListAsAdmin(pager);
		List<Order> result = new LinkedList<Order>();
		if(orders !=null){
			for(Order order:orders){
				result.add(getById(order.getOrder_id()));
			}
		}
		return result;
	}
	
	public void insert(Order order){
		orderDAO.insert(order);
	}
	
	public void insertItems(List<OrderItem> items){
		orderDAO.insertItems(items);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean updateInfo(Order order) throws Exception{
		cacheService.delete(generateKey(order.getOrder_id()));
		orderDAO.updateOrder(order);
		orderDAO.updateOrderItemsQuantity(order);
		Order temp = getById(order.getOrder_id());
		for(OrderItem item:temp.getItems()){
			messageService.sendOrderUpdateNotif(item.getUser_name(), order.getOrder_id());
		}
		return true;
	}
	
	public boolean deleteOrder(long order_id){
		try{
			orderDAO.deleteOrder(order_id);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean shipItem(OrderItem item) throws Exception{
			cacheService.delete(generateKey(item.getOrder_id()));
			orderDAO.shipItem(item);
			orderDAO.updateOrderStatus(item.getOrder_id());
			return true;
	}
	
	public boolean deleteItem(OrderItem item){
		try{
			cacheService.delete(generateKey(item.getOrder_id()));
			orderDAO.deleteItem(item);
			//todo.... update price and tax in orders
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private String generateKey(long order_id){
		return "order:"+order_id;
	}
}
