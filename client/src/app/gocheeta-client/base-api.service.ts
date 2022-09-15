import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AlertService } from "ngx-alerts"

@Injectable({
  providedIn: 'root'
})
export class BaseApiService {

  constructor(private http: HttpClient, private alertService: AlertService) { }
  
  get(path: any, isAuthorizedRequest: boolean): Observable<any> {
    if(isAuthorizedRequest) {
      return this.http.get(path, {
        headers: {
          "Authorization": this.tokenGenerator()
        }
      })
    } else {
      return this.http.get(path);
    }
  }

  post(path: any, isAuthorizedRequest:boolean, data: any): Observable<any> {
    if(isAuthorizedRequest) {
      return this.http.post(path, data, {
        headers: {
          "Authorization": this.tokenGenerator()
        }
      });
    } else {
      return this.http.post(path, data);
    }
  }

  put(path: any, isAuthorizedRequest: boolean, data: any) :Observable<any> {
    if(isAuthorizedRequest) {
      return this.http.put(path, data, {
        headers: {
          "Authorization": this.tokenGenerator()
        }
      }) 
    } else {
      return this.http.put(path, data);
    }
  }

  delete(path:any, isAuthorizedRequest: boolean, data: any): Observable<any> {
    if(isAuthorizedRequest) {
      return this.http.request("delete",path, {
        headers: {
          "Authorization": this.tokenGenerator()
        },
        body: data
      })
    } else {
      return this.http.request("delete",path,{
        body: data
      });
    }
  }

  private tokenGenerator() {
    if (sessionStorage.getItem("token") === undefined) {
      this.alertService.danger("Token is not provided, please log in to the system again");
    } else {
      return "Bearer " + sessionStorage.getItem("token");
    }
  }
}
