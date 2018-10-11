package com.capgemini.shoppingapp.service;

import java.util.List;
import java.util.Set;

import com.capgemini.shoppingapp.entities.Items;
import com.capgemini.shoppingapp.entities.Order;
import com.capgemini.shoppingapp.exception.OrderNotFoundException;

public interface OrderService {
	
	public Order placeOrder(Order order);

	public List<Order> getOrders( );

	public Order getOrderById(int orderId) throws OrderNotFoundException;

	public Order deleteOrderById(int orderId) throws OrderNotFoundException;

	public Order cancelOrderById(int orderId) throws OrderNotFoundException;

	public void addItems(Items items, int customerId);

	public void removeItems(Items items,int customerId);

	public Set<Items> getItems(int customerId);

	
}
