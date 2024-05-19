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
	private final CartMapper cartMapper;

	public Cart createCart(Customer customer) {
		Cart newCart = cartMapper.createCart(customer);
		return cartRepository.save(newCart);
	}

	public List<CartDto.List> selectCartList(Customer customer) {
		Cart validCart = cartRepository.getValidCartOrThrow(customer);
		return cartMapper.getAllCartItem(validCart.getCartItems());
	}

	public void addCartItem(String email, Long productId, int quantity) {
		// TODO 이전에 추가된 같은 상품이 있다면 수량만 업데이트 하기
		Customer validCustomer = customerRepository.getValidCustomerOrThrow(email, UserStatus.REGISTERED);
		Product validProduct = productRepository.getValidProductOrThrow(productId, RegisterStatus.REGISTERED);
		int currentQuantity = validProduct.getCapacity();

		Cart validCart = cartRepository.getValidCartOrThrow(validCustomer);
		if (isContainProductInCart(validCart.getCartItems(), validProduct)) {
			CartItem validCartItem = cartItemRepository.getValidCartItemWithProductIdOrThrow(validProduct.getId());
			validCartItem.increaseQuantity(quantity);
			cartItemRepository.save(validCartItem);
		} else {
			if (currentQuantity >= quantity) {
				CartItem newItem = cartMapper.addCart(validProduct, quantity);
				CartItem savedItem = cartItemRepository.save(newItem);
				cartRepository.findFirstByCustomer(validCustomer)
				  .ifPresentOrElse(cart -> cart.addItem(savedItem),
					() -> createCart(validCustomer).addItem(savedItem));
			} else {
				throw new ApiException(ApiErrorCode.INSUFFICIENT_STOCK);
			}
		}
	}

	public List<CartDto.List> quantityUpdate(Customer customer, Long cartItemId, String type) {
		CartItem validCartItem = cartItemRepository.getValidCartItemWithIdOrThrow(cartItemId);
		Product product = validCartItem.getProduct();
		int currentProductCapacity = product.getCapacity();
		int preQuantity = validCartItem.getQuantity();

		if (type.equals("increase")) {
			if (currentProductCapacity >= preQuantity + 1) {
				validCartItem.increaseQuantity(1);
			} else {
				throw new ApiException(ApiErrorCode.INSUFFICIENT_STOCK);
			}
		} else {
			if (preQuantity > 1) {
				validCartItem.decreaseQuantity(1);
			} else {
				deleteCartItem(customer, cartItemId);
			}
		}

		return selectCartList(customer);
	}

	public void deleteCartItem(Customer customer, Long carItemId) {
		Cart validCart = cartRepository.getValidCartOrThrow(customer);
		CartItem validCartItem = cartItemRepository.getValidCartItemWithIdOrThrow(carItemId);
		if (validCart.getCartItems().contains(validCartItem)) {
			validCart.removeItem(validCartItem);
			cartItemRepository.delete(validCartItem);
		}
	}

	public Boolean isContainProductInCart(List<CartItem> cartItemList, Product product) {
		return cartItemList.stream()
		  .anyMatch(cartItem -> cartItem.getProduct().getId().equals(product.getId()));
	}

}
