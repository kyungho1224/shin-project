package com.side.api.controller;

import com.side.api.service.CartService;
import com.side.api.service.CustomerService;
import com.side.db.dto.CustomerDto;
import com.side.db.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/open-api/customers")
@RestController
public class CustomerOpenApiController {

	private final CustomerService customerService;

	@PostMapping("/check-email")
	public ResponseEntity<Boolean> checkEmail(
	  @RequestParam String email
	) {
		return ResponseEntity.ok(customerService.isDuplicateEmail(email));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<CustomerDto.SignUpResponse> signUp(
	  @Validated
	  @RequestBody CustomerDto.SignUpRequest request
	) {
		CustomerDto.SignUpResponse response =
		  customerService.createCustomer(request.getEmail(), request.getPassword());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/sign-in")
	public ResponseEntity<CustomerDto.SignInResponse> signIn(
	  @Validated
	  @RequestBody CustomerDto.SignInRequest request
	) {
		CustomerDto.SignInResponse response =
		  customerService.loginCustomer(request.getEmail(), request.getPassword());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/temporary-member")
	public ResponseEntity<CustomerDto.SignUpResponse> tmpSignUp(
	  @RequestParam String email
	) {
		CustomerDto.SignUpResponse response = customerService.createTemporaryCustomer(email);
		return ResponseEntity.ok(response);
	}

}
