package com.order.service;

import java.util.List;

import com.order.entity.Item;

public interface CartService {

	public Item addItemToCart(Long productId, Integer quantity);

	public List<Item> getCart(Long cartId);

	public void changeItemQuantity(Long cartId, Integer quantity);
	
	public void deleteByItem(Long id);
	
	public List<Item> getAllCartByItem();

}
