package com.side.db.entity;

import com.side.db.constant.RegisterStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "reviews")
@Entity
public class Review extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private String title;

	private String contents;

	private String image;

	@Enumerated(EnumType.STRING)
	private RegisterStatus status;

	public static Review createOf() {
		return Review.builder()
		  .build();
	}

}
