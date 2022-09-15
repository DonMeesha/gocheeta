import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientBookingHistoryComponent } from './client-booking-history.component';

describe('ClientBookingHistoryComponent', () => {
  let component: ClientBookingHistoryComponent;
  let fixture: ComponentFixture<ClientBookingHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientBookingHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientBookingHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
