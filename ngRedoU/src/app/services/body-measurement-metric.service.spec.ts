import { TestBed } from '@angular/core/testing';

import { BodyMeasurementMetricService } from './body-measurement-metric.service';

describe('BodyMeasurementMetricService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BodyMeasurementMetricService = TestBed.get(BodyMeasurementMetricService);
    expect(service).toBeTruthy();
  });
});
