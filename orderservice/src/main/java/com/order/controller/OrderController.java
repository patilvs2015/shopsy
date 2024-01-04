package com.order.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Item;
import com.order.entity.Order;
import com.order.entity.OrderDetails;
import com.order.entity.User;
import com.order.feignclients.UserClients;
import com.order.httpheader.HeaderGenerator;
import com.order.repository.OrderDetailsRepo;
import com.order.service.CartService;
import com.order.service.OrderService;
import com.order.utilities.OrderUtilities;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private HeaderGenerator headerGenerator;

	@Autowired
	private UserClients userClients;

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;

	@Autowired
	private CartService cartService;

	@PostMapping("/order/{userId}")
	public ResponseEntity<Order> saveOrder(@PathVariable Long userId, HttpServletRequest req) {

		List<Item> item = cartService.getAllCartByItem();

		List<Object[]> userName = userClients.getUserNameById(userId);

		List<User> user = userName.stream().map(object -> {

			User user1 = new User();

			user1.setUserName((String) object[0]);

			return user1;

		}).collect(Collectors.toList());

		Order order1 = new Order();

		if (item != null && user != null) {

			for (User user2 : user) {
				order1 = this.createOrder(item, user2);
				try {
					orderService.saveOrder(order1);
					OrderDetails orderSave = this.saveDetailsByOrder(order1);
					orderDetailsRepo.save(orderSave);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		return new ResponseEntity<Order>(order1, headerGenerator.getHeadersForSuccessPostMethod(req, order1.getId()),
				HttpStatus.CREATED);

	}

	private Order createOrder(List<Item> item, User user) {

		Order order = new Order();
		order.setOrderedDate(LocalDate.now());

		order.setStatus("PAYMENT_EXPECTED");

		order.setTotal(OrderUtilities.countTotalPrice(item));

		order.setItems(item);
		order.setUser(user);

		return order;
	}

	private OrderDetails saveDetailsByOrder(Order order) {

		OrderDetails orderDetails = new OrderDetails();

		orderDetails.setUserName(order.getUser().getUserName());

		orderDetails.setOrderedDate(order.getOrderedDate());

		List<Item> items = order.getItems();

		for (Item item : items) {
			//orderDetails.setProductName(item.getProduct().getProductName());
			orderDetails.setQuantity(item.getQuantity());
			orderDetails.setSubTotal(item.getSubTotal());
			orderDetails.setPrice(item.getProduct().getPrice());
		}

		orderDetails.setTotal(order.getTotal());

		return orderDetails;

	}

}
