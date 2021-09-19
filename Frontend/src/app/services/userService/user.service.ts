import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebService } from './../webService/web.service';
import { User } from '../../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private webService: WebService) { }
  static httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
    'Accept': 'application/json'
  });
  
  URL = "http://localhost:8084/"
  login()
  {    
    this.webService.get<boolean>("user/loginDemo", new Map<string, any>()).subscribe(()=>{})
  }
  getAllUsers()
  {
    this.webService.get<boolean>("user/getAllUsers", new Map<string, any>()).subscribe(()=>{})
  }

}
