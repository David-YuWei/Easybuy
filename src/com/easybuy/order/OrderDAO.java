package com.easybuy.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.easybuy.common.Pager;
import com.easybuy.order.domain.Order;
import com.easybuy.order.domain.OrderItem;
import com.easybuy.shopcart.domain.ShopcartItem;

@Repository("order:orderDAO")
public class OrderDAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Order selectById(long order_id){
		return (Order)sqlSessionTemplate.selectOne("order.selectById", order_id);
	}
	
	public List<OrderItem> selectItemsById(long order_id){
		return (List<OrderItem>) sqlSessionTemplate.selectList("order.selectItemsById", order_id);
	}
	
	public List<Order> getListAsBuyer(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("order.selectListCountByBuyer",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Order>) sqlSessionTemplate.selectList("order.selectListByBuyer", props);
	}
	
	public List<Order> getListAsSeller(Pager pager,String user_name){
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("user_name", user_name);
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("order.selectListCountBySeller",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Order>) sqlSessionTemplate.selectList("order.selectListBySeller", props);
	}
	
	public List<Order> getListAsAdmin(Pager pager){
		Map<String, Object> props = new HashMap<String, Object>();
		if (pager != null) {
			Integer total = (Integer) sqlSessionTemplate.selectOne("order.selectListCountByAdmin",props);
			pager.setTotal(total);
			pager.limit();
			if (total > 0) {
				props.put("firstResult", pager.getPageFirstIndex());
				props.put("maxResult", pager.getPageSize());
			} else {
				return null;
			}
		}
		return (List<Order>) sqlSessionTemplate.selectList("order.selectListByAdmin", props);
	}
	
	public void insert(Order order){
		sqlSessionTemplate.insert("order.insert",order);
	}
	
	public void updateOrder(Order order){
		sqlSessionTemplate.update("order.updateOrder",order);
	}
	
	public void updateOrderItemsQuantity(Order order){
		List<OrderItem> items = order.getItems();
		sqlSessionTemplate.update("order.updateOrderItemsQuantity",items);
	}
	
	public void insertItems(List<OrderItem> items){
		sqlSessionTemplate.insert("order.insertItems",items);
	}
	
	public void deleteOrder(long order_id){
		sqlSessionTemplate.update("order.deleteOrder", order_id);
	}
	
	public void deleteItem(OrderItem item){
		sqlSessionTemplate.update("order.deleteItem",item);
	}
	
	public void shipItem(OrderItem item){
		sqlSessionTemplate.update("order.updateOrderItemStatus",item);
	}
	
	public void updateOrderStatus(long order_id){
		sqlSessionTemplate.update("order.updateOrderStatus",order_id);
	}
}

