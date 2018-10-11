package com.capgemini.shoppingapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.shoppingapp.entities.Items;
import com.capgemini.shoppingapp.entities.Order;
import com.capgemini.shoppingapp.exception.OrderNotFoundException;
import com.capgemini.shoppingapp.service.OrderService;

@RestController
public class OrderController {

	@Autowired	
	OrderService orderService;

	@PostMapping("/item")
	public ResponseEntity<Items> addItems(@RequestBody Items items, @RequestParam int customerId) {
		orderService.addItems(items, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/items")
	public ResponseEntity<Set<Items>> getItems(@RequestParam int customerId) {
		Set<Items> tempSet = orderService.getItems(customerId);
		return new ResponseEntity<Set<Items>>(tempSet, HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<Set<Items>> removeLineItem(@RequestParam int customerId, @RequestBody Items items) {
		orderService.removeItems(items, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/placeorder")
	public ResponseEntity<Order> placeorder(@RequestBody Order order) {
		order.setOrderDate(LocalDate.now());
		return new ResponseEntity<Order>(orderService.placeOrder(order), HttpStatus.OK);

	}
	
	
	@GetMapping("/cancel")
	public ResponseEntity<Order> cancelOrderById(@RequestParam int orderId) throws OrderNotFoundException {
		orderService.cancelOrderById(orderId);
		return new ResponseEntity<Order>(orderService.cancelOrderById(orderId), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Order> deleteOrderById(@RequestParam int orderId) throws OrderNotFoundException{
		orderService.deleteOrderById(orderId);
		return new ResponseEntity<Order>(orderService.deleteOrderById(orderId), HttpStatus.OK);
	}
	
	
	@GetMapping("/order")
	public ResponseEntity<Order> getorderById(@RequestParam int orderId) throws OrderNotFoundException{
		orderService.getOrderById(orderId);
		return new ResponseEntity<Order>(orderService.getOrderById(orderId),HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(){
	return new ResponseEntity<List<Order>>(orderService.getOrders(), HttpStatus.OK);
}
}
