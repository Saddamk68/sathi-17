package com.sathi.sim.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table("student_table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("id")
	private Long studentId;

	@Column("first_name")
	private String firstName;

	@Column("middle_name")
	private String middleName;

	@Column("last_name")
	private String lastName;

	@Column("father_name")
	private String fatherName;

	@Column("mother_name")
	private String motherName;

	@Temporal(TemporalType.DATE)
	@Column("dob")
	private LocalDate dob;

	@Column("gender")
	private String gender;

	@Column("school_name")
	private String schoolName;

	@Column("class_name")
	private Integer className;

	@Column("contact_num")
	private String contactNum;

	@Column("email")
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_subject_table", 
		joinColumns = @JoinColumn(name = "student_id"), 
		inverseJoinColumns = @JoinColumn(name = "sub_id"))
	private Set<Subject> subjects;

	@Column("is_active")
	private Boolean isActive;

	@Column("image_url")
	private String imageUrl;

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

//	public String getFullName() {
//		return (firstName != null ? firstName.strip() : "") 
//				+ (middleName != null ? " " + middleName.strip() : "")
//				+ (lastName != null ? " " + lastName.strip() : "");
//	}

}
