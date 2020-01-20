package com.redou.services;

import java.util.List;

import com.redou.entities.BodyMeasurementMetric;

public interface BodyMeasurementMetricService {
	public BodyMeasurementMetric getBodyMeasurementMetricById(int id);

	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUserId(int userId);

	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUsername(String username);

}
