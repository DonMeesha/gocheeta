import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/client/admin-profit-page', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: '/client/client-profile', title: 'User Profile',  icon:'person', class: '' },
    { path: '/client/bookings', title: 'Booking',  icon:'explore', class: '' },
    { path: '/client/branches', title: 'Branches',  icon:'public', class: '' },
    { path: '/client/vehicles', title: 'Vehicles',  icon:'drive_eta', class: '' },
    { path: '/client/drivers', title: 'Drivers',  icon:'transfer_within_a_station', class: '' },
    { path: '/client/distance-map', title: 'Distance Map',  icon:'room', class: '' },
    { path: '/client/customers', title: 'Customers',  icon:'interpreter_mode', class: '' },
    { path: '/client/booking-history', title: 'History',  icon:'local_library', class: '' },
    { path: '/logout', title: 'Logout',  icon:'logout', class: 'active-pro' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };

  logout(): void {
    sessionStorage.clear();
    const confirmed = confirm("Are you sure to log out from the system?");
    if (confirmed) {
      this.router.navigateByUrl("/login");
    } 
  }
}
