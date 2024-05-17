package com.side.db.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CustomerDto {

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SignUpRequest {
		@Email
		@NotBlank
		private String email;

		@NotBlank
		private String password;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SignUpResponse {
		private Long id;
		private String email;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SignInRequest {
		@Email
		@NotBlank
		private String email;

		@NotBlank
		private String password;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SignInResponse {
		private Long id;
		private String accessToken;
	}

}
