package com.ecommerce.ecommerce;

import java.util.Objects;

public class Order {
	int orderId;
	String orderName;
	String productName;
	String orderPlaced;
	String Description;
	double discount;
	double initialPrice;

	@Override
	public String toString() {
		return "Order{" +
				"orderName='" + orderName + '\'' +
				", productName='" + productName + '\'' +
				", orderPlaced='" + orderPlaced + '\'' +
				", Description='" + Description + '\'' +
				", discount=" + discount +
				", initialPrice=" + initialPrice +
				", orderId=" + orderId +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Double.compare(order.discount, discount) == 0 &&
				Double.compare(order.initialPrice, initialPrice) == 0 &&
				orderId == order.orderId &&
				Objects.equals(orderName, order.orderName) &&
				Objects.equals(productName, order.productName) &&
				Objects.equals(orderPlaced, order.orderPlaced) &&
				Objects.equals(Description, order.Description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderName, productName, orderPlaced,
				Description, discount, initialPrice, orderId);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderPlaced() {
		return orderPlaced;
	}

	public void setOrderPlaced(String orderPlaced) {
		this.orderPlaced = orderPlaced;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
}
