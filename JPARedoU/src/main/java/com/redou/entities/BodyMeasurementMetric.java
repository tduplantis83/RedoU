package com.redou.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="body_measurement_metric")
public class BodyMeasurementMetric {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	private LocalDate dateMeasured;
	
	private int heightMM;
	
	private double weightKg;
	
	private int waistMM;
	
	private Integer neckMM;
	
	private Integer shouldersMM;
	
	private Integer chestMM;
	
	private Integer bicepMM;
	
	private Integer hipsMM;
	
	private Integer thighMM;
	
	
	//CONSTRUCTOR
	public BodyMeasurementMetric() {
		
	}


	//GETS & SETS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public LocalDate getDateMeasured() {
		return dateMeasured;
	}


	public void setDateMeasured(LocalDate dateMeasured) {
		this.dateMeasured = dateMeasured;
	}


	public int getHeightMM() {
		return heightMM;
	}


	public void setHeightMM(int heightMM) {
		this.heightMM = heightMM;
	}


	public double getWeightKg() {
		return weightKg;
	}


	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}


	public int getWaistMM() {
		return waistMM;
	}


	public void setWaistMM(int waistMM) {
		this.waistMM = waistMM;
	}


	public Integer getNeckMM() {
		return neckMM;
	}


	public void setNeckMM(Integer neckMM) {
		this.neckMM = neckMM;
	}


	public Integer getShouldersMM() {
		return shouldersMM;
	}


	public void setShouldersMM(Integer shouldersMM) {
		this.shouldersMM = shouldersMM;
	}


	public Integer getChestMM() {
		return chestMM;
	}


	public void setChestMM(Integer chestMM) {
		this.chestMM = chestMM;
	}


	public Integer getBicepMM() {
		return bicepMM;
	}


	public void setBicepMM(Integer bicepMM) {
		this.bicepMM = bicepMM;
	}


	public Integer getHipsMM() {
		return hipsMM;
	}


	public void setHipsMM(Integer hipsMM) {
		this.hipsMM = hipsMM;
	}


	public Integer getThighMM() {
		return thighMM;
	}


	public void setThighMM(Integer thighMM) {
		this.thighMM = thighMM;
	}


	//HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicepMM == null) ? 0 : bicepMM.hashCode());
		result = prime * result + ((chestMM == null) ? 0 : chestMM.hashCode());
		result = prime * result + ((dateMeasured == null) ? 0 : dateMeasured.hashCode());
		result = prime * result + heightMM;
		result = prime * result + ((hipsMM == null) ? 0 : hipsMM.hashCode());
		result = prime * result + id;
		result = prime * result + ((neckMM == null) ? 0 : neckMM.hashCode());
		result = prime * result + ((shouldersMM == null) ? 0 : shouldersMM.hashCode());
		result = prime * result + ((thighMM == null) ? 0 : thighMM.hashCode());
		result = prime * result + userId;
		result = prime * result + waistMM;
		long temp;
		temp = Double.doubleToLongBits(weightKg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BodyMeasurementMetric other = (BodyMeasurementMetric) obj;
		if (bicepMM == null) {
			if (other.bicepMM != null)
				return false;
		} else if (!bicepMM.equals(other.bicepMM))
			return false;
		if (chestMM == null) {
			if (other.chestMM != null)
				return false;
		} else if (!chestMM.equals(other.chestMM))
			return false;
		if (dateMeasured == null) {
			if (other.dateMeasured != null)
				return false;
		} else if (!dateMeasured.equals(other.dateMeasured))
			return false;
		if (heightMM != other.heightMM)
			return false;
		if (hipsMM == null) {
			if (other.hipsMM != null)
				return false;
		} else if (!hipsMM.equals(other.hipsMM))
			return false;
		if (id != other.id)
			return false;
		if (neckMM == null) {
			if (other.neckMM != null)
				return false;
		} else if (!neckMM.equals(other.neckMM))
			return false;
		if (shouldersMM == null) {
			if (other.shouldersMM != null)
				return false;
		} else if (!shouldersMM.equals(other.shouldersMM))
			return false;
		if (thighMM == null) {
			if (other.thighMM != null)
				return false;
		} else if (!thighMM.equals(other.thighMM))
			return false;
		if (userId != other.userId)
			return false;
		if (waistMM != other.waistMM)
			return false;
		if (Double.doubleToLongBits(weightKg) != Double.doubleToLongBits(other.weightKg))
			return false;
		return true;
	}


	//TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BodyMeasurementMetric [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateMeasured=");
		builder.append(dateMeasured);
		builder.append(", heightMM=");
		builder.append(heightMM);
		builder.append(", weightKg=");
		builder.append(weightKg);
		builder.append(", waistMM=");
		builder.append(waistMM);
		builder.append(", neckMM=");
		builder.append(neckMM);
		builder.append(", shouldersMM=");
		builder.append(shouldersMM);
		builder.append(", chestMM=");
		builder.append(chestMM);
		builder.append(", bicepMM=");
		builder.append(bicepMM);
		builder.append(", hipsMM=");
		builder.append(hipsMM);
		builder.append(", thighMM=");
		builder.append(thighMM);
		builder.append("]");
		return builder.toString();
	}

}
