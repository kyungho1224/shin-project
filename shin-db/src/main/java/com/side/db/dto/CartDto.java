package com.side.db.dto;

import com.side.db.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartDto {

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class ItemRegisterRequest {
		private Product product;
		private int quantity;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class List {
		private Long id;
		private ProductDto.SimpleInfo product;
		private int quantity;
	}

}
