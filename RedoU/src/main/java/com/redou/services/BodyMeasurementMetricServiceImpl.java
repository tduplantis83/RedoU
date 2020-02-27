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
		if(measurement.getDateMeasured() == null) {
			measurement.setDateMeasured(LocalDate.now());
		}
		measurement.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(measurement);
	}

	@Override
	public BodyMeasurementMetric updateBodyMeasurementMetric(BodyMeasurementMetric measurement, int id) {
		BodyMeasurementMetric toUpdate = repo.findById(id);
		
		if(measurement.getUser() != null) {
			toUpdate.setUser(measurement.getUser());
		}
		//allow update of dateCreated incase wrong date was used when recored was created
		if(measurement.getDateMeasured() != null) {
			toUpdate.setDateMeasured(measurement.getDateMeasured());
		}
		//always change the dateUpdated
		toUpdate.setDateUpdated(LocalDate.now());
		if(measurement.getHeightMM() != 0) {
			toUpdate.setHeightMM(measurement.getHeightMM());
		}
		if(measurement.getWeightKg() > 0) {
			toUpdate.setWeightKg(measurement.getWeightKg());
		}
		if(measurement.getGoalWeightKg() != 0) {
			toUpdate.setGoalWeightKg(measurement.getGoalWeightKg());
		}
		if(measurement.getWaistMM() != 0) {
			toUpdate.setWaistMM(measurement.getWaistMM());
		}
		if(measurement.getNeckMM() != null) {
			toUpdate.setNeckMM(measurement.getNeckMM());
		}
		if(measurement.getShouldersMM() != null) {
			toUpdate.setShouldersMM(measurement.getShouldersMM());
		}
		if(measurement.getChestMM() != null) {
			toUpdate.setChestMM(measurement.getChestMM());
		}
		if(measurement.getBicepMM() != null) {
			toUpdate.setBicepMM(measurement.getBicepMM());
		}
		if(measurement.getHipsMM() != null) {
			toUpdate.setHipsMM(measurement.getHipsMM());
		}
		if(measurement.getThighMM() != null) {
			toUpdate.setThighMM(measurement.getThighMM());
		}
		
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
