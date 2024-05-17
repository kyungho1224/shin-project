package com.side.db.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CustomerRole {

	MEMBER("회원"),
	NON_MEMBER("비회원"),
	;

	private final String role;

}
