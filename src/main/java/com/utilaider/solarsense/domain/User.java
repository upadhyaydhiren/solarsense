package com.utilaider.solarsense.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * @author Dhiren
 * @Description This class maintain user and 
 */

@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	@Column(name = "user_name", unique = true)
	private String userName;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ElectronicMeter> meters;

	public User() {

	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setMeters(List<ElectronicMeter> meters) {
		this.meters = meters;
	}

	@JsonManagedReference("user_meter")
	public List<ElectronicMeter> getMeters() {
		return meters;
	}
}
