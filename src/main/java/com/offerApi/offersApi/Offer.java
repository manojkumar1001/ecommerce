package com.offerApi.offersApi;

import java.util.Objects;

public class Offer {
	int offerId;
	String offerName;
	String productName;
	String endDate;
	String Description;
	double discount;
	double initialPrice;

	@Override
	public String toString() {
		return "Offer{" +
				"offerName='" + offerName + '\'' +
				", productName='" + productName + '\'' +
				", endDate='" + endDate + '\'' +
				", Description='" + Description + '\'' +
				", discount=" + discount +
				", initialPrice=" + initialPrice +
				", offerId=" + offerId +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Offer offer = (Offer) o;
		return Double.compare(offer.discount, discount) == 0 &&
				Double.compare(offer.initialPrice, initialPrice) == 0 &&
				offerId == offer.offerId &&
				Objects.equals(offerName, offer.offerName) &&
				Objects.equals(productName, offer.productName) &&
				Objects.equals(endDate, offer.endDate) &&
				Objects.equals(Description, offer.Description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(offerName, productName, endDate,
				Description, discount, initialPrice, offerId);
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
}
