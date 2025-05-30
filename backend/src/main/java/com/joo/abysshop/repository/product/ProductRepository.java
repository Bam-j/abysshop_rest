package com.joo.abysshop.repository.product;

import com.joo.abysshop.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductName(String productName);

    @Query("SELECT p.price FROM Product p WHERE p.productId = :productId")
    Long findPriceByProductId(Long productId);

    @NonNull
    @EntityGraph(attributePaths = "productImage")
    Page<Product> findAll(@NonNull Pageable pageable);
}
