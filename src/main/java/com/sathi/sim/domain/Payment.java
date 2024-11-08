package com.sathi.sim.domain;

import java.time.LocalDate;
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
@Table("payment_table")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("id")
	private Long id;

	@Column("student_id")
	private Long studentId;

	@Column("payment_amount")
	private Double amount;

	@Temporal(TemporalType.DATE)
	@Column("payment_date")
	private LocalDate date;
	
	@Column("payment_method")
	private String paymentMethod;
	
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
