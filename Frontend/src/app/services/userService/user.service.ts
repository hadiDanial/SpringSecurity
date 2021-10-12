import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebService } from './../webService/web.service';
import { LoginResponse } from 'src/app/models/responses/LoginResponse';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private webService: WebService) { }

  login()
  {    
    let map = new Map();
    map.set("username","John");
    map.set("password","MagicalDaddy69");
    this.webService.post<LoginResponse>("auth/login", undefined, map).subscribe((response)=>{console.log(response)})
  }
  getAllUsers()
  {
    // this.webService.get<boolean>("user/getAllUsers", new Map<string, any>()).subscribe(()=>{})
  }

}
