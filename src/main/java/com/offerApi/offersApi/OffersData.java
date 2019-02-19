package com.offerApi.offersApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OffersData {
	private List<Offer> offerList;
	private static OffersData instance = new OffersData();
	private Map<String, String> currencyConversions;
	private Map<String, String> currencyCodes;
	private int count;

	private OffersData() {
		CurrencyConverter cc = new CurrencyConverter();
		this.count = 1;
		this.offerList = new ArrayList<>();
		this.currencyConversions = cc.getCurrencyConversions();
		this.currencyCodes = cc.getCurrencyCodes();
	}

	public Map<String, String> getCurrencyConversions() {
		return currencyConversions;
	}

	public Map<String, String> getCurrencyCodes() {
		return currencyCodes;
	}

	public int getCount() {
		return count;
	}

	public void setCurrencyConversions(Map<String, String> currencyConversions) {
		this.currencyConversions = currencyConversions;
	}

	public void setCount(int count){
		this.count = count;
	}

	public static OffersData getInstance(){
		return instance;
	}

	public List<Offer> getOfferList() {
		return offerList;
	}
}
