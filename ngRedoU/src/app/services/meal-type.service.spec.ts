import { TestBed } from '@angular/core/testing';

import { MealTypeService } from './meal-type.service';

describe('MealTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MealTypeService = TestBed.get(MealTypeService);
    expect(service).toBeTruthy();
  });
});
