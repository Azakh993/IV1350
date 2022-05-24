package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.ArrayList;
import java.util.List;

class DiscountDatabase {
	private static final DiscountDatabase DISCOUNT_DB = new DiscountDatabase();
	private final List<DiscountDTO> discounts = new ArrayList<>();

	private DiscountDatabase() {
		setDiscounts();
	}

	private void setDiscounts() {
		addTotalPriceDiscounts();
		addItemDiscounts();
	}

	private void addTotalPriceDiscounts() {
		discounts.add(new DiscountDTO(new Amount(1000), new Amount(0.05)));
		discounts.add(new DiscountDTO(new Amount(2000), new Amount(0.10)));
		discounts.add(new DiscountDTO(new Amount(3000), new Amount(0.15)));
	}

	private void addItemDiscounts() {
		discounts.add(new DiscountDTO("67334553", new Amount(2), new Amount(0.2)));
		discounts.add(new DiscountDTO("12312234", new Amount(3), new Amount(0.3)));
		discounts.add(new DiscountDTO("32784623", new Amount(2), new Amount(0.2)));
	}

	static DiscountDatabase getDiscountDB() {
		return DISCOUNT_DB;
	}

	DiscountDTO getDiscountDTO() {
		return new DiscountDTO(discounts);
	}
}