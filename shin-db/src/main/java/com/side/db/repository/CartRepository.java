package com.side.db.repository;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.entity.Cart;
import com.side.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findFirstByCustomer(Customer customer);

	default Cart getValidCartOrThrow(Customer customer) {
		return findFirstByCustomer(customer)
		  .orElseThrow(() -> new ApiException(ApiErrorCode.NOT_FOUND_CART));
	}

}
