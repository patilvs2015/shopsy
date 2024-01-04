package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	public List<Product> findAllByCategory(String category);
    public List<Product> findAllByProductName(String name);
    
    @Query("select e.productName from Product e where e.id = ?1")
    List<Object[]> findProductNameById(Long id);
    
    @Query("select e.productName, e.price from Product e where e.id = ?1")
    List<Object[]> findProductNameAndPriceById(Long id);
	
	/*
	 * @Query("select e.availability from Product e where e.id = ?1") public
	 * List<Product> findAvailabilityById(Long id);
	 */
	
}
