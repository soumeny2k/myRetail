package com.sample.myretail.repository;

import com.sample.myretail.domain.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This class is the repository class for Product.
 * This class has all CRUD method to handle MongoDB related operations.
 */
public interface ProductPriceRepository extends MongoRepository<ProductPrice, Long> { }