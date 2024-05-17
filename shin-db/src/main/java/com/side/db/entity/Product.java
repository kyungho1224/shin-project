package com.side.db.entity;

import com.side.common.exception.ApiErrorCode;
import com.side.common.exception.ApiException;
import com.side.db.constant.RegisterStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "products")
@Entity
public class Product extends BaseEntity {

	private String name;

	private String description;

	private Integer capacity;

	private BigDecimal price;

	private String thumbnail;

	@Enumerated(EnumType.STRING)
	private RegisterStatus registerStatus;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Builder.Default
	private List<Review> reviews = new ArrayList<>();

	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private Admin createdBy;

	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	private Admin updatedBy;

	public void sellStock(int count) {
		if (this.capacity >= count) {
			this.capacity -= count;
		} else {
			throw new ApiException(ApiErrorCode.INSUFFICIENT_STOCK);
		}
	}

	public void addStock(int count) {
		this.capacity += count;
	}

	public void unregister() {
		this.registerStatus = RegisterStatus.UNREGISTERED;
	}

}
