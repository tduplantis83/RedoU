package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.BodyMeasurementMetric;

public interface BodyMeasurementMetricRepo extends JpaRepository<BodyMeasurementMetric, Integer> {

	public BodyMeasurementMetric findById(int id);

	public List<BodyMeasurementMetric> findByUser_Id(int userId);

	public List<BodyMeasurementMetric> findByUser_UsernameIgnoreCase(String username);

}
