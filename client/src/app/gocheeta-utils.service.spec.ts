import { TestBed } from '@angular/core/testing';

import { GocheetaUtilsService } from './gocheeta-utils.service';

describe('GocheetaUtilsService', () => {
  let service: GocheetaUtilsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GocheetaUtilsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
