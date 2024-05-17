package com.side.api.controller;

import com.side.api.common.annotation.CurrentCustomer;
import com.side.api.service.CartService;
import com.side.api.service.CustomerDetails;
import com.side.api.service.ProductService;
import com.side.db.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/open-api/products")
@RestController
public class ProductOpenApiController {

	private final ProductService productService;
	private final CartService cartService;

	@GetMapping("")
	public ResponseEntity<Page<ProductDto.SimpleInfo>> getAllProducts(Pageable pageable) {
		Page<ProductDto.SimpleInfo> allProduct = productService.selectAllProducts(pageable);
		return ResponseEntity.ok(allProduct);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto.DetailInfo> getOneProduct(@PathVariable Long productId) {
		ProductDto.DetailInfo product = productService.selectOneProduct(productId);
		return ResponseEntity.ok(product);
	}

	@PostMapping("/{productId}/add-cart")
	public String addCart(
	  @CurrentCustomer CustomerDetails customer,
	  @PathVariable Long productId,
	  @RequestParam(required = false, defaultValue = "1") Integer quantity
	) {
		if (customer == null) {
			return "비회원입니다";
		} else {
			cartService.addCartItem(customer.getUsername(), productId, quantity);
			return "회원입니다";
		}
	}

}
