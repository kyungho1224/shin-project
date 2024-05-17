package com.side.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ApiErrorCode {

	DUPLICATION_CUSTOMER(HttpStatus.CONFLICT.value(), "중복된 사용자입니다"),
	NOT_FOUND_CUSTOMER(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다"),
	NOT_FOUND_STORE(HttpStatus.NOT_FOUND.value(), "상점을 찾을 수 없습니다"),
	NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND.value(), "상품을 찾을 수 없습니다"),
	NOT_FOUND_CART(HttpStatus.NOT_FOUND.value(), "장바구니를 찾을 수 없습니다"),
	NOT_FOUND_CART_ITEM(HttpStatus.NOT_FOUND.value(), "상품를 찾을 수 없습니다"),

	NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED.value(), "패스워드가 틀렸습니다"),

	TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "토큰 에러"),

	JSON_PARSING_ERROR(HttpStatus.BAD_REQUEST.value(), "json parsing error"),

	INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST.value(), "재고 수량이 부족합니다"),

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "server error"),
	FIREBASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Image upload error");;

	private final Integer errorCode;
	private final String errorMessage;

}
