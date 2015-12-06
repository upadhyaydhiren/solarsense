package com.utilaider.solarsense.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "electronic_meter_reading_data")
public class ElectronicMeterReadingData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "instantsurvey_meter_id", referencedColumnName = "meter_id")
	private ElectronicMeter instantSurveyDatameter;
	@ManyToOne
	@JoinColumn(name = "load_survey_meter_id", referencedColumnName = "meter_id")
	private ElectronicMeter loadSurveyDatameter;
	@Column(name = "current_reading_time")
	private Long cuurrentSnapTime;
	private Double voltage;
	private Double current;
	@Column(name = "power_factor")
	private Double powerFactor;
	private Double power;

	public void setCurrent(Double current) {
		this.current = current;
	}

	public Double getCurrent() {
		return current;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public void setPowerFactor(Double powerFactor) {
		this.powerFactor = powerFactor;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Double getPower() {
		return power;
	}

	public Double getPowerFactor() {
		return powerFactor;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setCuurrentSnapTime(Long cuurrentSnapTime) {
		this.cuurrentSnapTime = cuurrentSnapTime;
	}

	public Long getCuurrentSnapTime() {
		return cuurrentSnapTime;
	}

	public Long getId() {
		return id;
	}

	public void setInstantSurveyDatameter(ElectronicMeter instantSurveyDatameter) {
		this.instantSurveyDatameter = instantSurveyDatameter;
	}

	public void setLoadSurveyDatameter(ElectronicMeter loadSurveyDatameter) {
		this.loadSurveyDatameter = loadSurveyDatameter;
	}

	public ElectronicMeter getInstantSurveyDatameter() {
		return instantSurveyDatameter;
	}

	public ElectronicMeter getLoadSurveyDatameter() {
		return loadSurveyDatameter;
	}
}
