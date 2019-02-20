# E-commerce API
## prerequisites
- maven 
   - installation guide: https://www.baeldung.com/install-maven-on-windows-linux-mac
- restlet chrome extension to interact with ecommerce API
## Steps to Run API:
- clone this repository into your local computer
	- run the following commands sequentially
		- mvn clean package
		- cd target
		- java -jar ecommerceApi-0.0.1-SNAPSHOT.jar
## Usage of Api
- Basically there are three operations you can do with this API
	- place an order:
		- send a post request to http://localhost:8080/placeorder with order as body
		- example for order json:  
		    ```javascript
                    {"userName":"manoj",
                                 "order":{
                                          "orderName": "Black friday offer",
                                          "productName": "Mac book pro",
                                          "orderPlaced": "2019-03-01 22:30:00",
                                          "discount": 20,
                                          "initialPrice": 1000,
                                          "description": "happy thanks giving"
                                          }
                                }
                ```
		- note that :
			- discount is in percentage
	- getting order list:
		- send a get request to http://localhost:8080/allOrders?username=manoj&comparator=percentage
		- there are two request params for getorders path
		    - username
		        - to know the user Account
			- comparator:
				- this takes two values 
					- creationTime
					- percentage
				- creationTime sorts the orders based on creation time
				- percentage sorts the orders based on discount percentage
	- delete product:  
		- you can delete the orders explicitly by sending delete request to http://localhost:8080/deleteorder?orderId=1&username=manoj
		- here the request param orderId can be found from getorders path
	- createAccount:
	    - you can create Account by sending post request to http://localhost:8080/createAccount
	        - this path takes a json body 
	        - example json body
	            ```$javascript
	            {
                  "userName":"manoj",
                  "email" : "manojkumarjanagam@gmail.com"
                }  
                ```
    - getAccount:
        - you can get the details of your account and orders by get request to http://localhost:8080/getAccount?userName=manoj
        
			
