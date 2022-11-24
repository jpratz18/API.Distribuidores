package com.qacg.dealers.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
@Entity
@Table(name = "C_USER")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1171347728273220874L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", length = 45, nullable = false)
	private String name;

	@Column(name = "LASTNAME", length = 80, nullable = false)
	private String lastname;

	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;

	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@Column(name = "RETRIES")
	private Long retries;
	
	@Column(name = "PHONE", length = 12)
	private String phone;
	
	@Column(name = "AVATAR", length = 100)
	private String avatar;
	
	@CreatedDate
	@Column(name = "CREATED", nullable = false, updatable = false)
	private Date created;

	@LastModifiedDate
	@Column(name = "UPDATED", nullable = false)
	private Date updated;
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final User otherObject = (User) obj;

		return new EqualsBuilder()
				.append(this.getId(), otherObject.getId())
				.append(this.getName(), otherObject.getName())
				.append(this.getLastname(), otherObject.getLastname())
				.append(this.getEmail(), otherObject.getEmail())
				.append(this.getPassword(), otherObject.getPassword())
				.append(this.getEnabled(), otherObject.getEnabled())
				.append(this.getRetries(), otherObject.getRetries())
				.append(this.getPhone(), otherObject.getPhone())
				.append(this.getCreated(), otherObject.getCreated())
				.append(this.getUpdated(), otherObject.getUpdated())
				.append(this.getAvatar(), otherObject.getAvatar())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.getId())
				.append(this.getName())
				.append(this.getLastname())
				.append(this.getEmail())
				.append(this.getPassword())
				.append(this.getEnabled())
				.append(this.getRetries())
				.append(this.getPhone())
				.append(this.getAvatar())
				.append(this.getCreated())
				.append(this.getUpdated())
				.toHashCode();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", enabled=" + enabled + ", retries=" + retries + ", phone=" + phone + ", avatar=" + avatar
				+ ", created=" + created + ", updated=" + updated + "]";
	}
	
}
