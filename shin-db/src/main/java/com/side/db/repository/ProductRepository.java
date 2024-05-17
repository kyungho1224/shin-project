package com.side.db.repository;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.constant.RegisterStatus;
import com.side.db.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAllByRegisterStatusOrderByCreatedAtDesc(RegisterStatus registerStatus, Pageable pageable);

	Optional<Product> findFirstByIdAndRegisterStatus(Long productId, RegisterStatus registerStatus);

	default Product getValidProductOrThrow(Long productId, RegisterStatus registerStatus) {
		return findFirstByIdAndRegisterStatus(productId, registerStatus)
		  .orElseThrow(() -> new ApiException(ApiErrorCode.NOT_FOUND_PRODUCT));
	}

}
