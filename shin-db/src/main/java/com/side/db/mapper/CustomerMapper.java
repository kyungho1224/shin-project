package com.side.db.mapper;

import com.side.db.constant.CustomerRole;
import com.side.db.constant.UserStatus;
import com.side.db.dto.CustomerDto;
import com.side.db.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	Customer toCustomer(
	  String email, String password, CustomerRole customerRole, UserStatus userStatus
	);

	CustomerDto.SignUpResponse toSignUpResponse(Customer customer);

	Customer toCustomer(CustomerDto.SignInRequest signInRequest);

	CustomerDto.SignInResponse toSignInResponse(Customer customer, String accessToken);

	@Named("roleToString")
	default String customerRoleToString(CustomerRole customerRole) {
		return customerRole.name();
	}

	@Named("statusToString")
	default String userStatusToString(UserStatus userStatus) {
		return userStatus.name();
	}

}
