package com.order.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Order;
import com.order.repository.OrderRepo;
import com.order.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceimpl implements OrderService{
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}

}
