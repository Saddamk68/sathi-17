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
@Table("user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("user_id")
	private Long id;

	@Column("user_name")
	private String userName;

	@Column("user_first_name")
	private String firstName;

	@Column("user_last_name")
	private String lastName;

	@Column("user_email_address")
	private String email;

	@Column("user_password")
	private String userPassword;

	@Column("user_role")
	private String userRole;
	
	@Column("user_is_enabled")
	private Integer isEnabled;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("user_created_at")
	private LocalDateTime createdAt;

	@Column("user_created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("user_updated_at")
	private LocalDateTime updatedAt;

	@Column("user_updated_by")
	@LastModifiedBy
	private String updatedBy;

}
