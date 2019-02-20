package com.offerApi.offersApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OffersServiceTests {

	OffersService offersService = new OffersService();

	@Test
	public void deletefromListTest(){
		List<Offer> offerList = getOfferList();
		Offer offer = offersService.delteFromList("1", offerList);
		Offer o = getOffer(1);
		Assert.assertEquals(offer, o);
		Assert.assertEquals(offerList.size(),8);
	}

	@Test
	public void addToListTest(){
		List<Offer> offerList = getOfferList();
		Offer o = getOffer(10);
		offersService.addToList(o, offerList);
		Assert.assertTrue(offerList.contains(o));
		Assert.assertTrue(offerList.size()==10);
	}

	@Test
	public void testTimeLeft(){
		LocalDateTime now = LocalDateTime.of(2014, 9, 10, 6, 40, 45);
		LocalDateTime date = LocalDateTime.of(1984, 12, 16, 7, 45, 55);
		String timeLeft = offersService.getTimeLeft("2014-09-10 06:40:45", date);
		String expected = "29 years 8 months 24 days 22 hours 54 minutes 50 seconds.";
		Assert.assertEquals(expected,timeLeft);
	}

	private List<Offer> getOfferList(){
		List<Offer> offerList = new ArrayList<>();
		for(int i=1;i<10;i++){
			Offer o = getOffer(i);
			offerList.add(o);
		}
		return offerList;
	}

	private Offer getOffer(int i) {
		Offer o = new Offer();
		o.setOfferId(i);
		o.setDescription("hello world");
		o.setDiscount(10.0);
		o.setEndDate("2019-12-12 20:50:00");
		o.setInitialPrice(45.0);
		o.setOfferName("hello world this is manoj");
		o.setProductName("mac book pro");
		return o;
	}
}
