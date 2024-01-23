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

import lombok.Data;

@Data	
@Entity
@Table(name = "student_table")
@EntityListeners(AuditingEntityListener.class)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long studentId;

	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "middle_name", length = 30)
	private String middleName;

	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;

	@Column(name = "father_name", nullable = false)
	private String fatherName;

	@Column(name = "mother_name", nullable = false)
	private String motherName;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", nullable = false)
	private Date dob;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "school_name")
	private String schoolName;
	
	@Column(name = "class_name")
	private Integer className;

	@Column(name = "contact_num")
	private String contactNum;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "is_active", nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean isActive;
	
	@Column(name = "image_url")
	private String imageUrl;

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
