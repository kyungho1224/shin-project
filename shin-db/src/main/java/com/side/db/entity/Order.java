package com.side.db.entity;

import com.side.db.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Builder.Default
	private List<CartItem> cartItems = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private LocalDateTime orderDate;

	private LocalDateTime shipOut;

	private LocalDateTime deliveryBegins;

	private LocalDateTime deliveryCompleted;

	private String invoiceNumber;

	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	private Admin updatedBy;

	public static Order createOf() {
		return Order.builder()
		  .build();
	}

	public void updateStatus(OrderStatus status) {
		this.status = status;
		LocalDateTime now = LocalDateTime.now();

		switch (status) {
			case ORDER_COMPLETED:
				this.orderDate = now;
				break;
			case SHIP_OUT:
				this.shipOut = now;
				break;
			case DELIVERY_BEGINS:
				this.deliveryBegins = now;
				break;
			case DELIVERY_COMPLETED:
				this.deliveryCompleted = now;
				break;
			default:
				break;
		}
	}

}
