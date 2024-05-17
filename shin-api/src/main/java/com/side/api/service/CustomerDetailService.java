package com.side.api.service;

import com.side.db.constant.UserStatus;
import com.side.db.entity.Customer;
import com.side.db.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerDetailService implements UserDetailsService {

	private final CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.getValidCustomerOrThrow(username, UserStatus.REGISTERED);
		return CustomerDetails.builder().customer(customer).build();
	}

}
