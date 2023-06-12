package com.example.service;

import java.util.List;

import com.example.model.Order;

public interface OrderService {
	public List<Order> getAllOrder();
	
	public void addOneOrder(Order order);
	
	public void updateOneOrder(Integer id, Order order);
	
	public void deleteOneOrder(Integer OrderId);
	
	public Order findOneOrder(Integer orderId);
}
