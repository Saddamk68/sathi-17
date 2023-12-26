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
@ToString
@Entity
@Table(name = "parents_table")
@EntityListeners(AuditingEntityListener.class)
public class Parents {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parent_id", nullable = false)
	private Long parentId;

	@Column(name = "parent_name", nullable = false, length = 30)
	private String parentName;

	@Temporal(TemporalType.DATE)
	@Column(name = "parent_dob")
	private Date parentDOB;

	@Column(name = "parent_age")
	private Integer parentAge;

	@Column(name = "parent_contact_num")
	private Long parentContactNum;

	@Column(name = "user_email_address", length = 50)
	private String email;

	@Column(name = "parent_occupation", length = 50)
	private String occupation;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parent_created_at", nullable = false)
	private Date createdAt;

	@Column(name = "parent_created_by", nullable = false)
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parent_updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "parent_updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

}
