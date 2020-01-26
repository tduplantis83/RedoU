import { TestBed } from '@angular/core/testing';

import { PostTopicService } from './post-topic.service';

describe('PostTopicService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PostTopicService = TestBed.get(PostTopicService);
    expect(service).toBeTruthy();
  });
});
