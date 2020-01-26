import { TestBed } from '@angular/core/testing';

import { PostReplyService } from './post-reply.service';

describe('PostReplyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PostReplyService = TestBed.get(PostReplyService);
    expect(service).toBeTruthy();
  });
});
