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
@Table("parents_table")
public class Parents {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("parent_id")
	private Long parentId;

	@Column("parent_name")
	private String parentName;

	@Temporal(TemporalType.DATE)
	@Column("parent_dob")
	private LocalDate parentDOB;

	@Column("parent_age")
	private Integer parentAge;

	@Column("parent_contact_num")
	private Long parentContactNum;

	@Column("user_email_address")
	private String email;

	@Column("parent_occupation")
	private String occupation;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("parent_created_at")
	private LocalDateTime createdAt;

	@Column("parent_created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("parent_updated_at")
	private LocalDateTime updatedAt;

	@Column("parent_updated_by")
	@LastModifiedBy
	private String updatedBy;

}
