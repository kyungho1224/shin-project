package com.side.db.mapper;

import com.side.db.constant.CustomerRole;
import com.side.db.constant.UserStatus;
import com.side.db.dto.CustomerDto;
import com.side.db.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedSourcePolicy = ReportingPolicy.IGNORE,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerMapper {

	//	@Mappings(
//	  value = {
//		@Mapping(target = "profileImg", ignore = true),
//		@Mapping(target = "address", ignore = true),
//		@Mapping(target = "mobile", ignore = true),
//		@Mapping(target = "provider", ignore = true),
//		@Mapping(target = "providerId", ignore = true),
//		@Mapping(target = "orders", ignore = true),
//		@Mapping(target = "reviews", ignore = true)
//	  }
//	)
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
