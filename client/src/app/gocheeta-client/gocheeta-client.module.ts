import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingComponent } from './booking/booking.component';
import { GoCheetaClientRoutes } from './gocheeta-client.routing';
import { RouterModule } from '@angular/router';
import { ClientLayoutComponent } from './client-layout/client-layout.component';
import { BranchComponent } from './branch/branch.component';
import { DriversComponent } from './drivers/drivers.component';
import { CustomersComponent } from './customers/customers.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { DistanceMapComponent } from './distance-map/distance-map.component';
import { ClientBookingHistoryComponent } from './client-booking-history/client-booking-history.component';
import { AdminProfitDashboardComponent } from './admin-profit-dashboard/admin-profit-dashboard.component';
import { ClientProfileComponent } from './client-profile/client-profile.component';
import { HttpClientModule } from '@angular/common/http';
import { GocheetaUtilModule } from 'app/gocheeta-util/gocheeta-util.module';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatRippleModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BookingComponent,
    ClientLayoutComponent,
    BranchComponent,
    DriversComponent,
    CustomersComponent,
    VehicleComponent,
    DistanceMapComponent,
    ClientBookingHistoryComponent,
    AdminProfitDashboardComponent,
    ClientProfileComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(GoCheetaClientRoutes),
    HttpClientModule,
    GocheetaUtilModule,
    MatButtonModule,
    MatInputModule,
    MatRippleModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatSelectModule,
    FormsModule
  ]
})
export class GocheetaClientModule { }
