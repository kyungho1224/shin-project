package com.side.db.entity;

import com.side.db.constant.RegisterStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "notices")
@Entity
public class Notice extends BaseEntity {

	private String title;

	private String contents;

	private String image;

	@Enumerated(EnumType.STRING)
	private RegisterStatus status;

	private int hit;

	public static Notice createOf() {
		return Notice.builder()
		  .build();
	}

}
