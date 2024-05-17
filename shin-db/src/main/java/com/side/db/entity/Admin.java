package com.side.db.entity;

import com.side.db.constant.UserStatus;
import com.side.db.constant.AdminRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "admins")
@Entity
public class Admin extends BaseEntity {

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false)
	private String password;

	private String mobile;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AdminRole role;

}
