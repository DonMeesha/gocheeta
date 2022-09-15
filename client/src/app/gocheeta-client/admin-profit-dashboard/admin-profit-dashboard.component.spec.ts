import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProfitDashboardComponent } from './admin-profit-dashboard.component';

describe('AdminProfitDashboardComponent', () => {
  let component: AdminProfitDashboardComponent;
  let fixture: ComponentFixture<AdminProfitDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminProfitDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProfitDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
