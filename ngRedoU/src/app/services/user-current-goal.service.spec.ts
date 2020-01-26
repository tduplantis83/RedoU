import { TestBed } from '@angular/core/testing';

import { UserCurrentGoalService } from './user-current-goal.service';

describe('UserCurrentGoalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserCurrentGoalService = TestBed.get(UserCurrentGoalService);
    expect(service).toBeTruthy();
  });
});
