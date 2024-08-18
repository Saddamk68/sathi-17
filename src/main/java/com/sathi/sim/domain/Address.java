package com.sathi.sim.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table("address_table")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("id")
	private Long id;

	@Column("first_line")
	private String firstLine;

	@Column("second_line")
	private String secondLine;

	@Column("state")
	private String state;

	@Column("city")
	private String city;

	@Column("pin_code")
	private String pinCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("created_at")
	private LocalDateTime createdAt;

	@Column("created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("updated_at")
	private LocalDateTime updatedAt;

	@Column("updated_by")
	@LastModifiedBy
	private String updatedBy;
	
}
