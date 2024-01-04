package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.httpheader.HeaderGenarator;
import com.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductService productService;

	@Autowired
	private HeaderGenarator headerGenerator;

	@PostMapping(value = "/products")
	private ResponseEntity<Product> addProduct(@RequestBody Product product, HttpServletRequest request) {
		if (product != null) {
			try {
				productService.addProduct(product);
				return new ResponseEntity<Product>(product,
						headerGenerator.getHeadersForSuccessPostMethod(request, product.getId()), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Product>(headerGenerator.getHeadersForError(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Product>(headerGenerator.getHeadersForError(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/products/{id}")
	private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			try {
				productService.deleteProduct(id);
				return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/updateQuantity/{id}/{quantity}")
	public ResponseEntity<Product> updateQuantityById(@PathVariable Long id, @PathVariable int quantity,
			HttpServletRequest request) {

		Product product = productService.updateQuantityById(id, quantity);

		if (product != null) {
			return new ResponseEntity<Product>(product,
					headerGenerator.getHeadersForSuccessPostMethod(request, product.getId()), HttpStatus.CREATED);
		}
		return new ResponseEntity<Product>(product,
				headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	
	}

}
