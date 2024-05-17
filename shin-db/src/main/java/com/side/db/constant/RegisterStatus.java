package com.side.db.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RegisterStatus {

	REGISTERED("등록"),
	UNREGISTERED("삭제"),
	;

	private final String status;

}
