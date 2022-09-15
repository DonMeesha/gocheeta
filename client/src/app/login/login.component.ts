import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginModel } from 'app/data-models/login.model';
import { BaseApiService } from 'app/gocheeta-client/base-api.service';
import { GocheetaUtilsService } from 'app/gocheeta-utils.service';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginModel = new LoginModel();

  constructor(private goCheetaUtils: GocheetaUtilsService, private route: Router, private httpService: BaseApiService) { }

  ngOnInit(): void {
  }

  loginToSystem(): void {
    const isValid = this.validation();
    if (isValid) {
      this.goCheetaUtils.displaySpiner();
      this.httpService.post(`${environment.backendHost}${environment.REST_URLS.AUTH}`, false, this.login).subscribe(logedInfo => {
        this.goCheetaUtils.hideSpiner();
        sessionStorage.setItem("token", logedInfo.token);
        sessionStorage.setItem("role", btoa(logedInfo.roles[0].authority));
        this.route.navigateByUrl("/client/branches")
      }, error => {
        this.goCheetaUtils.hideSpiner();
        this.goCheetaUtils.displayUnsuccessMessage("Login is not success, please try again");
      });
    }
  }

  private validation(): boolean {
    let isValid = true;
    if (this.login.username === undefined || this.login.username.trim() === "") {
      isValid = false;
      this.goCheetaUtils.displayUnsuccessMessage("please give the email");
    }
    if (this.login.password === undefined || this.login.password.trim() === "") {
      isValid = false;
      this.goCheetaUtils.displayUnsuccessMessage("please give the password");
    }
    return isValid;
  }

}
