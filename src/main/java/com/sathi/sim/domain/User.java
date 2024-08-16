package com.sathi.sim.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Getter
//@Setter
//@ToString
@Data
@Table(name = "user_table")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "user_name", unique = true, nullable = false, length = 30)
	private String userName;

	@Column(name = "user_first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "user_last_name", nullable = false, length = 30)
	private String lastName;

	@Column(name = "user_email_address", length = 50)
	private String email;

	@Column(name = "user_password", nullable = false, length = 100)
	private String userPassword;

	@Column(name = "user_role", nullable = false, length = 20)
	private String userRole;
	
	@Column(name = "user_is_enabled", nullable = false)
	private Integer isEnabled;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_created_at", nullable = false)
	private Date createdAt;

	@Column(name = "user_created_by", nullable = false)
	@CreatedBy
	private String createdBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "user_updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getUserPassword() {
//		return userPassword;
//	}
//
//	public void setUserPassword(String userPassword) {
//		this.userPassword = userPassword;
//	}
//
//	public Date getCreatedAt() {
//		return userCreatedAt;
//	}
//
//	public void setCreatedAt(Date userCreatedAt) {
//		this.userCreatedAt = userCreatedAt;
//	}
//
//	public String getCreatedBy() {
//		return userCreatedBy;
//	}
//
//	public void setCreatedBy(String userCreatedBy) {
//		this.userCreatedBy = userCreatedBy;
//	}
//
//	public Date getUpdatedAt() {
//		return userUpdatedAt;
//	}
//
//	public void setUpdatedAt(Date userUpdatedAt) {
//		this.userUpdatedAt = userUpdatedAt;
//	}
//
//	public String getUpdatedBy() {
//		return userUpdatedBy;
//	}
//
//	public void setUpdatedBy(String userUpdatedBy) {
//		this.userUpdatedBy = userUpdatedBy;
//	}
//
//	public String getUserRole() {
//		return userRole;
//	}
//
//	public void setUserRole(String userRole) {
//		this.userRole = userRole;
//	}
//
//	@Override
//	public String toString() {
//		return "User {" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
//				+ ", email='" + email + '\'' + ", createdAt=" + userCreatedAt + ", createdBy='" + userCreatedBy + '\''
//				+ ", updatedAt=" + userUpdatedAt + ", updatedby='" + userUpdatedBy + '\'' + '}';
//	}

}
