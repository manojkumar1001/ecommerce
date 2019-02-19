package com.offerApi.offersApi;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OffersController {

	@Autowired
	OffersService offersService;

	@GetMapping("/getOffers")
	public ResponseEntity<String> getOffers(@RequestParam String currency, @RequestParam String comparator){
		String offersData = offersService.offerGetter(currency,comparator);
		return new ResponseEntity<>(offersData, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Offer> test(){
		Offer offer = new Offer();
		offer.setOfferId(1);
		offer.setOfferName("Black friday offer");
		offer.setEndDate("2019-01-02 22:30:00");
		offer.setDiscount(20.0);
		offer.setInitialPrice(100.0);
		offer.setProductName("Mac book pro");
		offer.setDescription("happy thanks giving");
		return new ResponseEntity<>(offer, HttpStatus.OK);
	}

	@GetMapping("/getCurrencies")
	public ResponseEntity<Map<String, String>> getCurrencies(){
		Map<String, String> currencyCodes = OffersData.getInstance().getCurrencyCodes();
		return new ResponseEntity(currencyCodes,HttpStatus.OK);
	}

	@PostMapping("/setOffers")
	public ResponseEntity setNewOffer(@RequestBody String jsonOffer){
		ObjectMapper objectMapper = new ObjectMapper();
		Offer offer;
		try {
			offer = objectMapper.readValue(jsonOffer, Offer.class);
			offersService.addToList(offer, OffersData.getInstance().getOfferList());
		} catch (IOException e) {
			e.printStackTrace();
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteOffer")
	public ResponseEntity<String> deleteOffer(String offerId){
		List<Offer> offerList = OffersData.getInstance().getOfferList();
		if(Integer.parseInt(offerId)<0||Integer.parseInt(offerId)>=offerList.size()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		offersService.delteFromList(offerId, offerList);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

}
