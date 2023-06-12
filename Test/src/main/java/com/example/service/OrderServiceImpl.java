package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public void addOneOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void updateOneOrder(Integer id, Order order) {
		Order myOrder = orderRepository.findById(id).orElse(null);
		if(myOrder != null)
			orderRepository.save(order);
		
	}

	@Override
	public void deleteOneOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public Order findOneOrder(Integer userId) {
		return orderRepository.findById(userId).orElse(null);
	}

}
