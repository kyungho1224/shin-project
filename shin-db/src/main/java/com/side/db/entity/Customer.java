package com.side.db.entity;

import com.side.db.constant.UserStatus;
import com.side.db.constant.CustomerRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "customers")
@Entity
public class Customer extends BaseEntity {

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	private String profileImg;

	private String address;

	private String mobile;

	private String provider;

	private String providerId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus userStatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CustomerRole customerRole;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Builder.Default
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Builder.Default
	private List<Review> reviews = new ArrayList<>();

}
