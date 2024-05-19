package com.side.api.controller;

import com.side.api.service.ProductService;
import com.side.db.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/open-api/customers/products")
@RestController
public class ProductOpenApiController {

	private final ProductService productService;

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

}
