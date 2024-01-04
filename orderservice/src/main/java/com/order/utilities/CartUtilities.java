package com.order.utilities;

import java.math.BigDecimal;

import com.order.entity.Product;

public class CartUtilities {

	public static BigDecimal getSubTotalForItem(Product product, int quantity) {

		return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));

	}
}