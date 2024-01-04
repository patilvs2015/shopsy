package com.product.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.httpheader.HeaderGenarator;
import com.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HeaderGenarator headerGenerator;

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProduct();
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getAllProductByCategory/{category}")
	public ResponseEntity<List<Product>> getAllProductBycategory(@PathVariable("category") String category) {

		List<Product> allProductByCategory = productService.getAllProductByCategory(category);

		if (!allProductByCategory.isEmpty()) {
			return new ResponseEntity<List<Product>>(allProductByCategory,
					headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);

	}

	@GetMapping(value = "/productsById/{id}")
	public ResponseEntity<Product> getOneProductById(@PathVariable("id") long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<Product>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/getAllProductsByName", params = "name")
	public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam("name") String name) {
		List<Product> products = productService.getAllProductsByName(name);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	// List<Object[]> findProductNameById(Long id)

	@GetMapping("/productName/{id}")
	public ResponseEntity<List<Object[]>> findProductNameById(@PathVariable("id") long id) {
		List<Object[]> ProductNameById = productService.findProductNameById(id);

		if (ProductNameById != null) {
			return new ResponseEntity<List<Object[]>>(ProductNameById, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Object[]>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	// getProductNameAndPriceById

	@GetMapping("/productNameAndPrice/{id}")
	public ResponseEntity<List<Object[]>> getProductNameAndPriceById(@PathVariable("id") long id) {
		List<Object[]> productNameAndPriceById = productService.getProductNameAndPriceById(id);

		if (productNameAndPriceById != null) {
			return new ResponseEntity<List<Object[]>>(productNameAndPriceById,
					headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<List<Object[]>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/updateQuantity/{id}/{quantity}")
	public ResponseEntity<Product> changeQuantityById(@PathVariable Long id, @PathVariable int quantity,
			HttpServletRequest req) {

		Product product = productService.changeQuantityById(id, quantity);

		if (product != null) {

			return new ResponseEntity<Product>(product, headerGenerator.getHeadersForSuccessPostMethod(req, id),
					HttpStatus.CREATED);
		}

		return new ResponseEntity<Product>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);

	}

}
