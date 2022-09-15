import { Routes } from '@angular/router';
import { AdminProfitDashboardComponent } from './admin-profit-dashboard/admin-profit-dashboard.component';
import { BookingComponent } from './booking/booking.component';
import { BranchComponent } from './branch/branch.component';
import { ClientBookingHistoryComponent } from './client-booking-history/client-booking-history.component';
import { ClientProfileComponent } from './client-profile/client-profile.component';
import { CustomersComponent } from './customers/customers.component';
import { DistanceMapComponent } from './distance-map/distance-map.component';
import { DriversComponent } from './drivers/drivers.component';
import { VehicleComponent } from './vehicle/vehicle.component';

export const GoCheetaClientRoutes: Routes = [
    {
        path: "bookings",
        component: BookingComponent,
    },
    {
        path: "branches",
        component: BranchComponent,
    },
    {
        path: "vehicles",
        component: VehicleComponent,
    }, 
    {
        path: "drivers",
        component: DriversComponent,
    },
    {
        path: "distance-map",
        component: DistanceMapComponent,
    },
    {
        path: "customers",
        component: CustomersComponent,
    },
    {
        path: "booking-history",
        component: ClientBookingHistoryComponent,
    },
    {
        path: "admin-profit-page",
        component: AdminProfitDashboardComponent,
    },
    {
        path: "client-profile",
        component: ClientProfileComponent
    }
]