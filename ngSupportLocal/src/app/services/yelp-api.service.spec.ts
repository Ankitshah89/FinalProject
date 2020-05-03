import { TestBed } from '@angular/core/testing';

import { YelpApiService } from './yelp-api.service';

describe('YelpApiService', () => {
  let service: YelpApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(YelpApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
