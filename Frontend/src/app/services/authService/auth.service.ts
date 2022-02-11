import { Injectable } from '@angular/core';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { LoginResponse } from 'src/app/models/responses/LoginResponse';
import { WebService } from '../webService/web.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  readonly PATH_URL = "auth";
  constructor(private webService: WebService) { }
  loginResponse : LoginResponse | undefined;
  
  public login(loginRequest: LoginRequest)
  {
    // if(this.loginResponse != undefined)
    //   return;
    localStorage.clear();
    let map = new Map<string, any>();
    map.set("loginRequest", loginRequest);
    this.webService.post<LoginResponse>(this.PATH_URL + "/login", map).subscribe(response=>
      {
        console.log("Login message: " + response.$message);
        this.loginResponse = response;
        localStorage.setItem("token",response.token.accessToken);
      });
    }

  }
