package com.product.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.entity.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
@Transactional
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public List<Product> getAllProductByCategory(String category) {

		return productRepository.findAllByCategory(category);
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getAllProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findAllByProductName(name);
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);

	}

	@Override
	public List<Object[]> findProductNameById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findProductNameById(id);
	}

	@Override
	public List<Object[]> getProductNameAndPriceById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findProductNameAndPriceById(id);
	}

	@Override
	public Product changeQuantityById(Long id, int quantity) {

		Product product = productRepository.findById(id).get();

		int i = product.getAvailability() - quantity;
		product.setAvailability(i);

		return productRepository.save(product);

	}

	@Override
	public Product updateQuantityById(Long id, int quantity) {
		Optional<Product> product = productRepository.findById(id);
		Product product2 = product.get();
		product2.setAvailability(quantity);

		return productRepository.save(product2);
	}

}
