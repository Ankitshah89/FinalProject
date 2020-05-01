import { TestBed } from '@angular/core/testing';

import { ArticleCommentService } from './article-comment.service';

describe('ArticleCommentService', () => {
  let service: ArticleCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArticleCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
