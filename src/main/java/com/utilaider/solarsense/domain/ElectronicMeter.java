package com.utilaider.solarsense.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "electornic_meter")
public class ElectronicMeter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meter_id")
	private Long id;
	@Column(unique = true, nullable = false, name = "meter_serial_no")
	private String meterSerialNo;
	private Double latitude;
	private Double longitude;
	@Enumerated(EnumType.STRING)
	@Column(name = "electronic_meter_type")
	private ElectronicMeterType electronicMeterType;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setMeterSerialNo(String meterSerialNo) {
		this.meterSerialNo = meterSerialNo;
	}

	public String getMeterSerialNo() {
		return meterSerialNo;
	}

	public Long getId() {
		return id;
	}

	public void setElectronicMeterType(ElectronicMeterType electronicMeterType) {
		this.electronicMeterType = electronicMeterType;
	}

	public ElectronicMeterType getElectronicMeterType() {
		return electronicMeterType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonBackReference("user_meter")
	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
