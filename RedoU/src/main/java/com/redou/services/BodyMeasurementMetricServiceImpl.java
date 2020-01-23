package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.BodyMeasurementMetric;
import com.redou.repositories.BodyMeasurementMetricRepo;

@Service
public class BodyMeasurementMetricServiceImpl implements BodyMeasurementMetricService{

	@Autowired
	private BodyMeasurementMetricRepo repo;
	
	@Override
	public BodyMeasurementMetric getBodyMeasurementMetricById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}
	

	@Override
	public BodyMeasurementMetric createBodyMeasurementMetric(BodyMeasurementMetric measurement) {
		measurement.setDateMeasured(LocalDate.now());
		measurement.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(measurement);
	}

	@Override
	public BodyMeasurementMetric updateBodyMeasurementMetric(BodyMeasurementMetric measurement, int id) {
		BodyMeasurementMetric toUpdate = repo.findById(id);
		
		toUpdate.setUser(measurement.getUser());
		//allow update of dateCreated incase wrong date was used when recored was created
		toUpdate.setDateMeasured(measurement.getDateMeasured());
		toUpdate.setDateUpdated(LocalDate.now());
		toUpdate.setHeightMM(measurement.getHeightMM());
		toUpdate.setWeightKg(measurement.getWeightKg());
		toUpdate.setWaistMM(measurement.getWaistMM());
		toUpdate.setNeckMM(measurement.getNeckMM());
		toUpdate.setShouldersMM(measurement.getShouldersMM());
		toUpdate.setChestMM(measurement.getChestMM());
		toUpdate.setBicepMM(measurement.getBicepMM());
		toUpdate.setHipsMM(measurement.getHipsMM());
		toUpdate.setThighMM(measurement.getThighMM());
		
		return repo.saveAndFlush(toUpdate);

	}

	@Override
	public boolean deleteBodyMeasurementMetric(int id) {
		try {
			BodyMeasurementMetric toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	

}
