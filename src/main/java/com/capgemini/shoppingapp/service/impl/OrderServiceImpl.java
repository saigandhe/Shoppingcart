package com.capgemini.shoppingapp.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.shoppingapp.entities.Items;
import com.capgemini.shoppingapp.entities.Order;
import com.capgemini.shoppingapp.exception.OrderNotFoundException;
import com.capgemini.shoppingapp.repository.OrderRepository;
import com.capgemini.shoppingapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order placeOrder(Order order) {
		
//		order.setCustomerId(customerId);
//		order.setItems(map.get(customerId));
//		order.setOrderAmount(15000);
		order.setOrderDate(LocalDate.now());
		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
		
	}

	@Override
	public Order getOrderById(int orderId) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
		return optionalOrder.get();
		}else {
			throw new OrderNotFoundException("Order dose not exist");
	}
	}

	@Override
	public Order deleteOrderById(int orderId) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			optionalOrder.get().setStatus("deleted");
			orderRepository.save(optionalOrder.get());
		}else
		throw new OrderNotFoundException("Order dosen't exists");
		return optionalOrder.get();
		}

	@Override
	public Order cancelOrderById(int orderId) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			optionalOrder.get().setStatus("cancelled");
			orderRepository.save(optionalOrder.get());
		}else
		throw new OrderNotFoundException("Order dosen't exists");
		return optionalOrder.get();
	}

	private HashMap<Integer, Set<Items>> map = new HashMap<>();
	
	@Override
	public void addItems(Items items , int customerId) {
		Set<Items> tempitem = map.get(customerId);
		if(tempitem == null) {
			tempitem = new HashSet<>();
			tempitem.add(items);
			map.put(customerId, tempitem);
		}else {
			tempitem.add(items);
			map.put(customerId, tempitem);
		}
	}
	
	@Override
	public Set<Items> getItems( int customerId) {
		Set<Items> tempitem = map.get(customerId);
		return tempitem;
	}

	@Override
	public void removeItems( Items items,int customerId) {
	  Set<Items> tempitem = map.get(customerId);
	  if(tempitem != null) {
		
		tempitem.remove(items);
		map.put(customerId, tempitem);
	}

	
	}
	
}
