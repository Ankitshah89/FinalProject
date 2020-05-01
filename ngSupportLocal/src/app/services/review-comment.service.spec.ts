import { TestBed } from '@angular/core/testing';

import { ReviewCommentService } from './review-comment.service';

describe('ReviewCommentService', () => {
  let service: ReviewCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReviewCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
