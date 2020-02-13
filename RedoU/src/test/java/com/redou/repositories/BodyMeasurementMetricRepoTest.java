package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.redou.entities.BodyMeasurementMetric;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class BodyMeasurementMetricRepoTest {

	@Autowired
	private BodyMeasurementMetricRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		BodyMeasurementMetric b = repo.findById(1);
		assertEquals(94.26, b.getWeightKg());
	}

	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<BodyMeasurementMetric> b = repo.findByUser_Id(2);
		assertEquals(94.26, b.get(0).getWeightKg());
	}
	
	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<BodyMeasurementMetric> b = repo.findByUser_UsernameIgnoreCase("TRAVISd");
		assertEquals(94.26, b.get(0).getWeightKg());
	}
	
}
