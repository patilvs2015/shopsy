package com.order.serviceimpl;

import java.math.BigDecimal;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Item;
import com.order.entity.Product;
import com.order.feignclients.ProductClient;
import com.order.repository.ItemRepo;
import com.order.repository.ProductRepo;
import com.order.service.CartService;
import com.order.utilities.CartUtilities;
import com.order.utilities.MathUtilities;


@Service
public class CartServiceimpl implements CartService {

	@Autowired
	private ProductClient productClient;

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Item addItemToCart(Long productId, Integer quantity) {

		List<Object[]> productNameAndPrice = productClient.getProductNameAndPriceById(productId);

		// List<Product> product1 = new ArrayList<>();

		/*
		 * productNameAndPrice.forEach(object1 -> {
		 * 
		 * Product product = new Product();
		 * 
		 * product.setProductName((String) object1[0]);
		 * 
		 * product.setPrice((BigDecimal) object1[1]);
		 * 
		 * product1.add(product);
		 * 
		 * });
		 */

		List<Product> collectproduct = productNameAndPrice.stream().map(object -> {
			Product product = new Product();
			product.setProductName((String) object[0]);

			BigDecimal price = MathUtilities.getBigDecimal(object[1]);
			product.setPrice(((BigDecimal) price));

			return product;

		}).collect(Collectors.toList());

		Item item = new Item();

		for (Product product2 : collectproduct) {

			item = new Item(quantity, CartUtilities.getSubTotalForItem(product2, quantity), product2);
		}

		return itemRepo.save(item);

	}

	@Override
	public List<Item> getCart(Long cartId) {
		return itemRepo.findAll();

	}

	@Override
	public void changeItemQuantity(Long cartId, Integer quantity) {
		Item item2 = itemRepo.findById(cartId).get();

		if (item2 != null) {

			item2.setQuantity(quantity);
			item2.setSubTotal(CartUtilities.getSubTotalForItem(item2.getProduct(), quantity));
			itemRepo.save(item2);
		}
		

	}

	

	@Override
	public List<Item> getAllCartByItem() {
		// TODO Auto-generated method stub
		return itemRepo.findAll();
	}

	@Override
	public void deleteByItem(Long id) {
		itemRepo.deleteById(id);
		
	}

	

}
