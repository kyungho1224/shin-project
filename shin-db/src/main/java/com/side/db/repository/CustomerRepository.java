package com.side.db.repository;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.constant.UserStatus;
import com.side.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	boolean existsByEmail(String email);

	Optional<Customer> findFirstByEmailAndUserStatus(String email, UserStatus userStatus);

	default Customer getValidCustomerOrThrow(String email, UserStatus userStatus) {
		return findFirstByEmailAndUserStatus(email, userStatus)
		  .orElseThrow(() -> new ApiException(ApiErrorCode.NOT_FOUND_CUSTOMER));
	}

}
