package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Item;
import com.order.entity.Product;
import com.order.feignclients.ProductClient;
import com.order.httpheader.HeaderGenerator;
import com.order.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductClient productClient;

	@Autowired
	private CartService cartService;

	@Autowired
	private HeaderGenerator headerGenerator;

	@PostMapping("/addItem/{productId}/{quantity}")
	public ResponseEntity<Item> addItemToCart(@PathVariable Long productId, @PathVariable Integer quantity,
			HttpServletRequest request) {

		Product product = productClient.changeQuantityById(productId, quantity);
		Item item = new Item();
		if (product != null) {
			item = cartService.addItemToCart(productId, quantity);
		} else {
			new Exception("QUANTITY IS ZERO , No AVAIBALE QUANTITY");
		}

		return new ResponseEntity<Item>(item, headerGenerator.getHeadersForSuccessPostMethod(request, productId),
				HttpStatus.CREATED);

	}

	@GetMapping("/getCart/{cartId}")
	public ResponseEntity<List<Item>> getCart(@PathVariable Long cartId) {
		List<Item> cart = cartService.getCart(cartId);

		return new ResponseEntity<List<Item>>(cart, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
	}

	@PostMapping("/changeQua/{cartId}/{quantity}")
	public ResponseEntity<Void> changeItemQuantity(@PathVariable Long cartId, @PathVariable Integer quantity,
			HttpServletRequest request) {

		cartService.changeItemQuantity(cartId, quantity);

		return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessPostMethod(request, cartId), HttpStatus.OK);

	}

	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
		cartService.deleteByItem(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Item>> getAllCartByItem() {
		List<Item> item = cartService.getAllCartByItem();

		return new ResponseEntity<List<Item>>(item, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);

	}

}
