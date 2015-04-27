package com.easybuy.wishlist;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easybuy.common.Pager;
import com.easybuy.product.ProductDAO;
import com.easybuy.product.ProductService;
import com.easybuy.product.domain.Product;
import com.easybuy.wishlist.domain.WishlistItem;


@Service("wishlist:wishlistService")
public class WishlistService {

	@Resource(name = "wishlist:wishlistDAO")
	private WishlistDAO wishlistDAO;
	
	public List<Product> getList(Pager pager, String user_name){
		return wishlistDAO.getByUsername(pager,user_name);
	}
	
	public boolean insertItem(WishlistItem wishlistItem){
		try{
			wishlistDAO.insertItem(wishlistItem);
			return true;
		}
		catch(Exception e){
			return true;
		}
	}
	
	public boolean deleteItem(WishlistItem wishlistItem){
		try{
			wishlistDAO.deleteItem(wishlistItem);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
}
