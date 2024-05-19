package com.side.db.repository;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findFirstById(Long carItemId);

	Optional<CartItem> findFirstByProductId(Long productId);

	default CartItem getValidCartItemWithIdOrThrow(Long cartItemId) {
		return findFirstById(cartItemId)
		  .orElseThrow(() -> new ApiException(ApiErrorCode.NOT_FOUND_CART_ITEM));
	}

	default CartItem getValidCartItemWithProductIdOrThrow(Long productId) {
		return findFirstByProductId(productId)
		  .orElseThrow(() -> new ApiException(ApiErrorCode.NOT_FOUND_CART_ITEM));
	}

}
