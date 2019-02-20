package com.ecommerce.ecommerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/allOrders")
	public ResponseEntity<String> getOrders(@RequestParam String username, @RequestParam String comparator){
		String offersData = orderService.offerGetter(username,comparator);
		return new ResponseEntity<>(offersData, HttpStatus.OK);
	}

	@PostMapping("/placeOrder")
	public ResponseEntity placeOrder(@RequestBody String jsonOrder){
		ObjectMapper objectMapper = new ObjectMapper();
		Order order;
		try {
			JsonNode jsonNode = objectMapper.readTree(jsonOrder);
			JsonNode nameJson = jsonNode.get("userName");
			JsonNode orderJson = jsonNode.get("order");
			order = objectMapper.treeToValue(orderJson, Order.class);
			String userName = objectMapper.treeToValue(nameJson, String.class);
			Map<String, Account> accountMap = OrdersData.getInstance().getAccountMap();
			if(accountMap.containsKey(userName)) {
				orderService.addToList(order, accountMap.get(userName).getOrders());
			} else {
				return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (IOException e) {
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteOrder")
	public ResponseEntity<Order> deleteOffer(@RequestParam String orderId, @RequestParam String username){
		Map<String, Account> accountMap = OrdersData.getInstance().getAccountMap();
		if(Integer.parseInt(orderId)<=0){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(accountMap.containsKey(username)){
			Account account = accountMap.get(username);
			List<Order> orderList = account.getOrders();
			Order order = orderService.deleteFromList(orderId, orderList);
			if(order ==null){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAccount")
	public ResponseEntity<Account> getAccountDetails(@RequestParam String userName){
		Map<String, Account> accountMap = OrdersData.getInstance().getAccountMap();
		if(accountMap.containsKey(userName)){
			return new ResponseEntity<>(accountMap.get(userName),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/createAccount")
	public ResponseEntity createAccount(@RequestBody String accountInfoJson){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Account account = objectMapper.readValue(accountInfoJson, Account.class);
			account.setOrders(new ArrayList<>());
			if(OrdersData.getInstance().getAccountMap().containsKey(account.getUserName())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			OrdersData.getInstance().getAccountMap().put(account.getUserName(),account);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
