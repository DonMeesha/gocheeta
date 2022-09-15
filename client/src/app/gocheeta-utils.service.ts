import { Injectable } from '@angular/core';
import { AlertService } from 'ngx-alerts';
import { NgxSpinnerService } from 'ngx-spinner';
import { BaseApiService } from './gocheeta-client/base-api.service';

@Injectable({
  providedIn: 'root'
})
export class GocheetaUtilsService {

  constructor(private spinner: NgxSpinnerService, private alertService: AlertService) { }

  displaySpiner(): void {
    this.spinner.show();
  }

  hideSpiner(): void {
    this.spinner.hide();
  }

  displaySuccessMessage(message: string): void {
    this.alertService.success(message);
  }

  displayUnsuccessMessage(message: string): void {
    this.alertService.danger(message);
  }

  displayInfoMessage(message: string): void {
    this.alertService.info(message);
  }

  displayWarningMessage(message: string): void {
    this.alertService.warning(message);
  }

  getBase64ImageString(evt: any): string {
    let base64String = "";
    const file = evt.target.files[0];
    if (!file) {
        return null;
    }
    const reader = new FileReader();
    reader.onload = () => {
      base64String = reader.result as string;
    };
    return base64String;
  }
}
