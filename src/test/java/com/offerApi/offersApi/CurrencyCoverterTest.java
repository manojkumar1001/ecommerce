package com.offerApi.offersApi;

import java.util.Map;

import org.junit.Test;

public class CurrencyCoverterTest {
	CurrencyConverter cc = new CurrencyConverter();
	@Test
	public void test(){
		Map<String, String> currencyConversions = cc.getCurrencyConversions();
		System.err.println(currencyConversions);
	}


}
