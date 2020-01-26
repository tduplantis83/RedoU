import { TestBed } from '@angular/core/testing';

import { DailyCaloricIntakeService } from './daily-caloric-intake.service';

describe('DailyCaloricIntakeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DailyCaloricIntakeService = TestBed.get(DailyCaloricIntakeService);
    expect(service).toBeTruthy();
  });
});
