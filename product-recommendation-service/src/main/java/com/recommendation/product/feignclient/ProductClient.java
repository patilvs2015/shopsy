package com.recommendation.product.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recommendation.product.model.Product;


@FeignClient(name = "product-catalog-service", url = "http://localhost:8810/")
public interface ProductClient {

  //  @GetMapping ("/products/productsById/{id}")
  //  public Product getProductById(@PathVariable("id") Long productId);
	
	 @GetMapping ("/products/productName/{id}")
	List<Object[]>findProductNameById(@PathVariable("id") long id);

}
