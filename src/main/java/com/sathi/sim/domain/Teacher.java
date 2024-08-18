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
@Table("teacher_table")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column("tchr_id")
	private Long teacherId;

	@Column("tchr_name")
	private String teacherName;

	@Column("tchr_gender")
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column("tchr_dob")
	private LocalDate tchrDOB;

	@Column("tchr_age")
	private Integer age;

	@Column("tchr_contact_num")
	private Long contactNum;

	@Column("tchr_email_id")
	private String email;

	@Column("tchr_sub_id")
	private Long tchrSubId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("tchr_created_at")
	private LocalDateTime createdAt;

	@Column("tchr_created_by")
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column("tchr_updated_at")
	private LocalDateTime updatedAt;

	@Column("tchr_updated_by")
	@LastModifiedBy
	private String updatedBy;

}
