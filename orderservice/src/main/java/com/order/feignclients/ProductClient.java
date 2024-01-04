package com.order.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.entity.Product;

@FeignClient(name = "product-catlog-service", url = "http://localhost:8810/")
public interface ProductClient {

	@GetMapping(value = "products/productNameAndPrice/{id}")
	public List<Object[]> getProductNameAndPriceById(@PathVariable(value = "id") Long productId);
	
	
	@PostMapping("products/updateQuantity/{id}/{quantity}")
	public Product changeQuantityById(@PathVariable Long id,@PathVariable int quantity);

}
