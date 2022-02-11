import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebService } from './../webService/web.service';
import { LoginResponse } from 'src/app/models/responses/LoginResponse';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { User } from 'src/app/models/entities/User';
import { AuthService } from '../authService/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private webService: WebService, private authService: AuthService) { }

  login(username:string, password:string)
  {    
    let req = new LoginRequest(username, password);
    this.authService.login(req);
  }
  getAllUsers()
  {
    this.webService.get<User[]>("user/getAllUsers", new Map<string, any>()).subscribe((users)=>{console.log(users)})
  }

}
