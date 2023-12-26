package com.sathi.sim.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "subject_table")
@EntityListeners(AuditingEntityListener.class)
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sub_id", nullable = false)
	private Long id;

	@Column(name = "sub_name", unique = true, nullable = false, length = 50)
	private String subName;

	@Column(name = "sub_code", length = 30)
	private String subCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub_created_datetime", nullable = false)
	private Date subCreatedAt;

	@Column(name = "sub_created_by", nullable = false)
	@CreatedBy
	private String subCreatedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub_updated_datetime", nullable = false)
	private Date subUpdatedAt;

	@Column(name = "sub_updated_by", nullable = false)
	@LastModifiedBy
	private String subUpdatedBy;

}
