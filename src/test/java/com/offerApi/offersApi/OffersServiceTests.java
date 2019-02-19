package com.offerApi.offersApi;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class OffersServiceTests {

	OffersService offersService = new OffersService();
	@Test
	public void testTimeLeft(){
		LocalDateTime now = LocalDateTime.of(2014, 9, 10, 6, 40, 45);
		LocalDateTime date = LocalDateTime.of(1984, 12, 16, 7, 45, 55);
		String timeLeft = offersService.getTimeLeft("2014-09-10 06:40:45", date);
		String expected = "29 years 8 months 24 days 22 hours 54 minutes 50 seconds.";
		Assert.assertEquals(expected,timeLeft);
	}

	@Test
	public void getStringForOfferTest(){
		Offer offer = new Offer();
		offer.setOfferId(1);
		offer.setOfferName("Black friday offer");
		offer.setEndDate("2019-03-01 22:30:00");
		offer.setDiscount(20.0);
		offer.setInitialPrice(100.0);
		offer.setProductName("Mac book pro");
		offer.setDescription("happy thanks giving");
		String offerString = offersService.getStringForOffer(offer, "INR");
		System.err.println(offerString);
	}

}
