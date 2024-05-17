package com.side.db.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AdminRole {

	MASTER("마스터"),
	MANAGER("매니저"),
	;

	private final String role;

}
