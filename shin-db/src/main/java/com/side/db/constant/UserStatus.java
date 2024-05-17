package com.side.db.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserStatus {

	NOT_CERTIFICATED("인증 대기"),
	REGISTERED("가입"),
	UNREGISTERED("탈퇴"),
	;

	private final String status;

}
