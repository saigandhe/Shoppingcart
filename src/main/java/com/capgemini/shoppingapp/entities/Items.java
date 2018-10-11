package com.capgemini.shoppingapp.entities;


import org.springframework.data.annotation.Id;


public class Items {
	
	@Id
	
	private int quantity;
	private int productId;

	public Items() {
		super();

	}

	public Items(int quantity, int productId) {
		super();
		this.quantity = quantity;
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	

}
