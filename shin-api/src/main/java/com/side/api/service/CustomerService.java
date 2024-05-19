package com.side.api.service;

import com.side.api.util.JwtProvider;
import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.constant.UserStatus;
import com.side.db.constant.CustomerRole;
import com.side.db.dto.CustomerDto;
import com.side.db.entity.Customer;
import com.side.db.mapper.CustomerMapper;
import com.side.db.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService {

	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final CustomerRepository customerRepository;
	private final CartService cartService;
	private final CustomerMapper customerMapper;

	public boolean isDuplicateEmail(String email) {
		return customerRepository.existsByEmail(email);
	}

	public CustomerDto.SignUpResponse createCustomer(String email, String password) {
		if (isDuplicateEmail(email)) {
			throw new ApiException(ApiErrorCode.DUPLICATION_CUSTOMER);
		}
		Customer customer = customerMapper.toCustomer(
		  email, passwordEncoder.encode(password),
		  CustomerRole.MEMBER, UserStatus.REGISTERED
		);
		Customer savedCustomer = customerRepository.save(customer);
		cartService.createCart(savedCustomer);
		return customerMapper.toSignUpResponse(savedCustomer);
	}

	public void verifyCustomer(String uuid) {
		// TODO 인증받은 사용자로 변경하기
		Customer customer = null;
		cartService.createCart(customer);
	}

	@Transactional(readOnly = true)
	public CustomerDto.SignInResponse loginCustomer(String email, String password) {
		Customer validCustomer = customerRepository.getValidCustomerOrThrow(email, UserStatus.REGISTERED);
		if (passwordEncoder.matches(password, validCustomer.getPassword())) {
			return customerMapper.toSignInResponse(validCustomer, jwtProvider.generatedToken(email));
		}
		throw new ApiException(ApiErrorCode.NOT_MATCH_PASSWORD);
	}

	public CustomerDto.SignUpResponse createTemporaryCustomer(String email) {
		if (isDuplicateEmail(email)) {
			throw new ApiException(ApiErrorCode.DUPLICATION_CUSTOMER);
		}
		Customer tmpCustomer = customerMapper.toCustomer(
		  email, passwordEncoder.encode(email.split("@")[1]),
		  CustomerRole.NON_MEMBER, UserStatus.NOT_CERTIFICATED
		);
		Customer savedCustomer = customerRepository.save(tmpCustomer);
		cartService.createCart(savedCustomer);
		return customerMapper.toSignUpResponse(savedCustomer);
	}

}
