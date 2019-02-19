# Offers API

## prerequisites
- maven 
   - installation guide: https://www.baeldung.com/install-maven-on-windows-linux-mac
- restlet chrome extension to interact with offers API
## Steps to Run API:
- clone this repository into your local computer
	- run the following commands sequentially
		- mvn clean package
		- cd target
		- java -jar offersApi-0.0.1-SNAPSHOT.jar
## Usage of Api
- Basically there are three operations you can do with this API
	- creating an offer:
		- send a post request to http://localhost:8080/setOffers with offer as body
		- example for offer json:  
				```
				{"offerName": "Black friday offer","productName": "Mac book pro","endDate": "2019-03-03 22:30:00","discount": 22,
"initialPrice": 100,"description": "happy thanks giving"}
				```  
			- note that :
				- discount is in percentage
				- initial price is in dollars
	- getting offer list:
		- send a get request to http://localhost:8080/getOffers?currency=INR&comparator=percentage
		- there are to request params for getOffers path
			- currency:
				- currency decides in what currency you need to print the offer
				- you can get currency codes of different countries from http://localhost:8080/getCurrencies
				- currency conversion rates are updated from other currency api which gives the values accurately
			- comparator:
				- this takes two values 
					- creationTime
					- percentage
				- creationTime sorts the offers based on creation time
				- percentage sorts the offers based on discount percentage
	- delete offer:  
		- you can delete the offers explicitly by sending delete request to http://localhost:8080/deleteOffer?offerId=1
		- here the request param offerId can be found from getOffers path
			
