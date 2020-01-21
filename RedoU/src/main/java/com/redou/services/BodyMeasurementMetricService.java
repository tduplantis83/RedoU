package com.redou.services;

import java.util.List;

import com.redou.entities.BodyMeasurementMetric;

public interface BodyMeasurementMetricService {
	public BodyMeasurementMetric getBodyMeasurementMetricById(int id);

	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUserId(int userId);

	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUsername(String username);
	
	public BodyMeasurementMetric createBodyMeasurementMetric(BodyMeasurementMetric measurement);

	public BodyMeasurementMetric updateBodyMeasurementMetric(BodyMeasurementMetric measurement);
	
	public boolean deleteBodyMeasurementMetric(int id);
}
