package com.offerApi.offersApi;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OffersService {

	public void delteFromList(String offerId, List<Offer> offerList){
		List<Offer> offerToBeDeleted = offerList.stream()
				.filter(m -> m.getProductName().equalsIgnoreCase(offerId))
				.collect(Collectors.toList());
		int index = offerList.indexOf(offerToBeDeleted.get(0));
		offerList.remove(index);
	}

	public void addToList(Offer offer, List<Offer> offerList){
		OffersData offersData = OffersData.getInstance();
		offer.setOfferId(offersData.getCount());
		offersData.setCount(offersData.getCount() + 1);
		offerList.add(offer);
	}

	public String offerGetter(String currency, String comparator){
		if(currency==null){
			currency = "USD";
		}
		if(comparator==null){
			comparator = "creationTime";
		}
		StringBuilder stringBuilder = new StringBuilder();
		List<Offer> offerList = OffersData.getInstance().getOfferList();
		if(comparator.equalsIgnoreCase("creationTime")){
			offerList = offerList.stream().sorted(Comparator.comparing(m -> m.getOfferId())).collect(Collectors.toList());
		} else if(comparator.equalsIgnoreCase("percentage")){
			offerList = offerList.stream().sorted(Comparator.comparing(m -> m.getDiscount())).collect(Collectors.toList());
		}
		for(Offer offer : offerList){
			stringBuilder.append(getStringForOffer(offer, currency));
		}
		return stringBuilder.toString();
	}

	String getStringForOffer(Offer offer, String currency) {
		Map<String, String> currencyConversions = OffersData.getInstance().getCurrencyConversions();
		DecimalFormat df = new DecimalFormat("#.##");
		String conversion = String.valueOf(currencyConversions.get("USD" + currency));
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("OFFER ID:\t");
		stringBuilder.append(offer.getOfferId());
		stringBuilder.append("\n");
		stringBuilder.append("NAME:\t");
		stringBuilder.append(offer.getOfferName());
		stringBuilder.append("\n");
		stringBuilder.append("DESCRIPTION:\t");
		stringBuilder.append(offer.getDescription());
		stringBuilder.append("\n");
		stringBuilder.append("PRODUCT:\t");
		stringBuilder.append(offer.getProductName());
		stringBuilder.append("\n");
		stringBuilder.append("Initial Price:\t");
		double initialPrice = offer.getInitialPrice();
		initialPrice = initialPrice*Double.parseDouble(conversion);
		stringBuilder.append(df.format(initialPrice));
		stringBuilder.append("\t");
		stringBuilder.append(currency);
		stringBuilder.append("\n");
		stringBuilder.append("DISCOUNT:\t");
		stringBuilder.append(offer.getDiscount());
		stringBuilder.append("\t%\n");
		stringBuilder.append("Discounted Price:\t");
		double discountedPrice = offer.getInitialPrice() - offer.getDiscount()*(offer.getInitialPrice()/100);
		discountedPrice = discountedPrice*Double.parseDouble(conversion);
		stringBuilder.append(df.format(discountedPrice));
		stringBuilder.append("\t");
		stringBuilder.append(currency);
		stringBuilder.append("\n");
		stringBuilder.append("\n");
		stringBuilder.append("TIME LEFT:\t");
		String timeLeft = this.getTimeLeft(offer.getEndDate(), LocalDateTime.now());
		stringBuilder.append(timeLeft);
		stringBuilder.append("\n==========================================================\n\n");
		return stringBuilder.toString();
	}

	private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
		LocalDateTime today = LocalDateTime.of(now.getYear(),
				now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
		Duration duration = Duration.between(today, now);
		long seconds = duration.getSeconds();
		long minusDays = 0;
		if(seconds<0){
			now = now.minusDays(1);
			today = LocalDateTime.of(now.getYear(),
					now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
			minusDays = 1;
			now = now.plusDays(1);
			seconds = Duration.between(today, now).getSeconds();
		}

		long hours = seconds / (3600);
		long minutes = ((seconds % 3600) / 60);
		long secs = (seconds % 60);

		return new long[]{hours, minutes, secs, minusDays};
	}

	public String getTimeLeft(String dateString, LocalDateTime now){
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(dateString, df);
		Period between = Period.between(now.toLocalDate(), date.toLocalDate());
		long[] time = getTime(now, date);
		return between.getYears() + " years " +
				between.getMonths() + " months " +
				(between.getDays() - time[3]) + " days " +
				time[0] + " hours " +
				time[1] + " minutes " +
				time[2] + " seconds.";
	}
}
