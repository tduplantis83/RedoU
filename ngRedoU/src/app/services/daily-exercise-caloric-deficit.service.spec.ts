import { TestBed } from '@angular/core/testing';

import { DailyExerciseCaloricDeficitService } from './daily-exercise-caloric-deficit.service';

describe('DailyExerciseCaloricDeficitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DailyExerciseCaloricDeficitService = TestBed.get(DailyExerciseCaloricDeficitService);
    expect(service).toBeTruthy();
  });
});
