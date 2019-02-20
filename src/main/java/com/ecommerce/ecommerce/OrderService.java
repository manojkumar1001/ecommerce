package com.ecommerce.ecommerce;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	public Order deleteFromList(String offerId, List<Order> orderList){
		List<Order> orderToBeDeleted = orderList.stream()
				.filter(m -> m.orderId == Integer.parseInt(offerId))
				.collect(Collectors.toList());
		if(orderToBeDeleted.size()==0){
			return null;
		}
		int index = orderList.indexOf(orderToBeDeleted.get(0));
		return orderList.remove(index);
	}

	public void addToList(Order order, List<Order> orderList){
		OrdersData ordersData = OrdersData.getInstance();
		order.setOrderId(ordersData.getCount());
		ordersData.setCount(ordersData.getCount() + 1);
		orderList.add(order);
	}

	public String offerGetter(String username, String comparator){
		if(comparator==null){
			comparator = "creationTime";
		}
		StringBuilder stringBuilder = new StringBuilder();
		Account account = OrdersData.getInstance().getAccountMap().get(username);
		List<Order> orderList = account.getOrders();
		if(comparator.equalsIgnoreCase("creationTime")){
			orderList = orderList.stream().sorted(Comparator.comparing(Order::getOrderId)).collect(Collectors.toList());
		} else if(comparator.equalsIgnoreCase("percentage")){
			orderList = orderList.stream().sorted(Comparator.comparing(Order::getDiscount)).collect(Collectors.toList());
		}
		stringBuilder.append(account.userName);
		stringBuilder.append("\n");
		stringBuilder.append(account.email);
		stringBuilder.append("\n");
		stringBuilder.append("*****************************************************\n");
		for(Order order : orderList){
			String stringForOffer = getStringForOffer(order);
			if(stringForOffer==null){
				continue;
			}
			stringBuilder.append(stringForOffer);
		}
		return stringBuilder.toString();
	}

	String getStringForOffer(Order order) {
		DecimalFormat df = new DecimalFormat("#.##");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("OFFER ID:\t");
		stringBuilder.append(order.getOrderId());
		stringBuilder.append("\n");
		stringBuilder.append("NAME:\t");
		stringBuilder.append(order.getOrderName());
		stringBuilder.append("\n");
		stringBuilder.append("DESCRIPTION:\t");
		stringBuilder.append(order.getDescription());
		stringBuilder.append("\n");
		stringBuilder.append("PRODUCT:\t");
		stringBuilder.append(order.getProductName());
		stringBuilder.append("\n");
		stringBuilder.append("Initial Price:\t");
		double initialPrice = order.getInitialPrice();
		stringBuilder.append(df.format(initialPrice));
		stringBuilder.append("\t");
		stringBuilder.append("\n");
		stringBuilder.append("DISCOUNT:\t");
		stringBuilder.append(order.getDiscount());
		stringBuilder.append("\t%\n");
		stringBuilder.append("Discounted Price:\t");
		double discountedPrice = order.getInitialPrice() - order.getDiscount()*(order.getInitialPrice()/100);
		stringBuilder.append(df.format(discountedPrice));
		stringBuilder.append("\t");
		stringBuilder.append("\n");
		stringBuilder.append("\n");
		stringBuilder.append("TIME PLACED:\t");
		stringBuilder.append(order.getOrderPlaced());
		stringBuilder.append("\n==========================================================\n\n");
		return stringBuilder.toString();
	}
}
