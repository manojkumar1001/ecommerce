package com.ecommerce.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OffersServiceTests {

	OrderService orderService = new OrderService();

	@Test
	public void deletefromListTest(){
		List<Order> orderList = getOrderList();
		Order order = orderService.deleteFromList("1", orderList);
		Order o = getOrder(1);
		Assert.assertEquals(order, o);
		Assert.assertEquals(orderList.size(),8);
	}

	@Test
	public void addToListTest(){
		List<Order> orderList = getOrderList();
		Order o = getOrder(10);
		orderService.addToList(o, orderList);
		Assert.assertTrue(orderList.contains(o));
		Assert.assertTrue(orderList.size()==10);
	}

	private List<Order> getOrderList(){
		List<Order> orderList = new ArrayList<>();
		for(int i=1;i<10;i++){
			Order o = getOrder(i);
			orderList.add(o);
		}
		return orderList;
	}

	private Order getOrder(int i) {
		Order o = new Order();
		o.setOrderId(i);
		o.setDescription("hello world");
		o.setDiscount(10.0);
		o.setOrderPlaced("2019-12-12 20:50:00");
		o.setInitialPrice(45.0);
		o.setOrderName("hello world this is manoj");
		o.setProductName("mac book pro");
		return o;
	}
}
