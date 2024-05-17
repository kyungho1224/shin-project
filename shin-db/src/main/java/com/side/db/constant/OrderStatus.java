package com.side.db.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

	ORDER_COMPLETED("주문 완료"),
	SHIP_OUT("출고 완료"),
	DELIVERY_BEGINS("배송 시작"),
	DELIVERY_COMPLETED("배송 완료"),
	;

	private final String status;

}
