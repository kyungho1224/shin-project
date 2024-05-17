package com.side.api.controller;

import com.side.api.common.annotation.CurrentCustomer;
import com.side.api.service.CartService;
import com.side.api.service.CustomerDetails;
import com.side.db.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/carts")
@RestController
public class CartApiController {

	private final CartService cartService;

	@GetMapping("")
	public ResponseEntity<List<CartDto.List>> getAllCartItemList(
	  @CurrentCustomer CustomerDetails customer
	) {
		List<CartDto.List> list = cartService.selectCartList(customer.getCustomer());
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<Void> removeCartItem(
	  @CurrentCustomer CustomerDetails customer,
	  @PathVariable Long cartItemId
	) {
		cartService.deleteCartItem(customer.getCustomer(), cartItemId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
