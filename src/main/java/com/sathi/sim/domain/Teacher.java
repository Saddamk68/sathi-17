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
@Table(name = "teacher_table")
@EntityListeners(AuditingEntityListener.class)
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tchr_id", nullable = false)
	private Long teacherId;

	@Column(name = "tchr_name", nullable = false, length = 30)
	private String teacherName;

	@Column(name = "tchr_gender", nullable = false, length = 10)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "tchr_dob")
	private Date tchrDOB;

	@Column(name = "tchr_age")
	private Integer age;

	@Column(name = "tchr_contact_num")
	private Long contactNum;

	@Column(name = "tchr_email_id", length = 50)
	private String email;

	@Column(name = "tchr_sub_id")
	private Long tchrSubId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tchr_created_at", nullable = false)
	private Date createdAt;

	@Column(name = "tchr_created_by", nullable = false)
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tchr_updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "tchr_updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

}
