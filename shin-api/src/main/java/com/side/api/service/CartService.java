package com.side.api.service;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.constant.RegisterStatus;
import com.side.db.constant.UserStatus;
import com.side.db.dto.CartDto;
import com.side.db.entity.Cart;
import com.side.db.entity.CartItem;
import com.side.db.entity.Customer;
import com.side.db.entity.Product;
import com.side.db.mapper.CartMapper;
import com.side.db.repository.CartItemRepository;
import com.side.db.repository.CartRepository;
import com.side.db.repository.CustomerRepository;
import com.side.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CartService {

	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;

	public Cart createCart(Customer customer) {
		// 장바구니 생성
		Cart newCart = CartMapper.INSTANCE.createCart(customer);
		return cartRepository.save(newCart);
	}

	public List<CartDto.List> selectCartList(Customer customer) {
		// 회원 장바구니 목록 조회
		Cart validCart = cartRepository.getValidCartOrThrow(customer);
		return CartMapper.INSTANCE.getAllCartItem(validCart.getCartItems());
	}

	public void addCartItem(String email, Long productId, int quantity) {
		// 장바구니 담기, 카트아이템 생성 후 저장하고 추가
		Customer validCustomer = customerRepository.getValidCustomerOrThrow(email, UserStatus.REGISTERED);
		Product validProduct = productRepository.getValidProductOrThrow(productId, RegisterStatus.REGISTERED);
		int currentQuantity = validProduct.getCapacity();

		/* TODO 주문에서 처리하기 */
//		validProduct.sellStock(quantity);
//		productRepository.save(validProduct);

		if (currentQuantity >= quantity) {
			CartItem newItem = CartMapper.INSTANCE.addCart(validProduct, quantity);
			CartItem savedItem = cartItemRepository.save(newItem);
			cartRepository.findFirstByCustomer(validCustomer)
			  .ifPresentOrElse(cart -> cart.addItem(savedItem),
				() -> createCart(validCustomer).addItem(savedItem));
		} else {
			throw new ApiException(ApiErrorCode.INSUFFICIENT_STOCK);
		}

	}

	public void updateCartItem(Long productId, int quantity) {

	}

	public void increaseCount() {

	}

	public void decreaseCount() {

	}

	public void deleteCartItem(Customer customer, Long carItemId) {
		Cart validCart = cartRepository.getValidCartOrThrow(customer);
		CartItem validCartItem = cartItemRepository.getValidCartItemOrThrow(carItemId);
		if (validCart.getCartItems().contains(validCartItem)) {
			validCart.removeItem(validCartItem);
			cartItemRepository.delete(validCartItem);
		}
	}

}
