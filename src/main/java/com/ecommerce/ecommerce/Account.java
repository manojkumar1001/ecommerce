package com.ecommerce.ecommerce;

import java.util.List;
import java.util.Objects;

public class Account {
	String userName;
	String email;
	List<Order> orders;

	public Account() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(userName, account.userName) &&
				Objects.equals(email, account.email) &&
				Objects.equals(orders, account.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, email, orders);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
