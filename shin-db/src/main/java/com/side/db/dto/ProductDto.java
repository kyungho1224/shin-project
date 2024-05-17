package com.side.db.dto;

import com.side.db.constant.RegisterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class ProductDto {

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class RegisterRequest {
		private String name;
		private String description;
		private Integer capacity;
		private BigDecimal price;
		private String thumbnail;
		private RegisterStatus registerStatus;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class RegisterResponse {
		private Long id;
		private String name;
		private String description;
		private Integer capacity;
		private BigDecimal price;
		private String thumbnail;
		private RegisterStatus registerStatus;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SimpleInfo {
		private Long id;
		private String name;
		private Integer capacity;
		private BigDecimal price;
		private String thumbnail;
		private RegisterStatus registerStatus;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class DetailInfo {
		private Long id;
		private String name;
		private String description;
		private Integer capacity;
		private BigDecimal price;
		private String thumbnail;
		private RegisterStatus registerStatus;
		/* TODO List<Review> 추가하기 */
	}

}
