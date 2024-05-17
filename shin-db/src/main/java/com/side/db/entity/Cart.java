package com.side.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "carts")
@Entity
public class Cart extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", unique = true)
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Builder.Default
	private List<CartItem> cartItems = new ArrayList<>();

	public void addItem(CartItem cartItem) {
		this.cartItems.add(cartItem);
	}

	public void removeItem(CartItem cartItem) {
		this.cartItems.remove(cartItem);
	}

}
