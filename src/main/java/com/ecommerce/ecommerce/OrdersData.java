package com.ecommerce.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class OrdersData {
	private Map<String, Account> accountMap;
	private static OrdersData instance = new OrdersData();
	private int count;

	private OrdersData() {
		this.count = 1;
		this.accountMap = new HashMap<>();
	}

	public Map<String, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<String, Account> accountMap) {
		this.accountMap = accountMap;
	}

	public static OrdersData getInstance() {
		return instance;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
