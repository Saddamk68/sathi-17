package com.sathi.sim.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data	
@Entity
@Table(name = "fee_table")
@EntityListeners(AuditingEntityListener.class)
public class FeeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "student_id", nullable = false)
	private Long studentId;

	@Column(name = "fee_total", nullable = false)
	private Double totalFee;

	@Column(name = "fee_deposit_amt")
	private Double depositFeeAmt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fee_deposit_date")
	private Date depositFeeDate;

	@Column(name = "fee_remaining_amt", nullable = false)
	private Double remainingFeeAmt;

	@Temporal(TemporalType.DATE)
	@Column(name = "fee_remaining_date")
	private Date remainingFeeDate;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;
	
}
