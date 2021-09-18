import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  static httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
    'Accept': 'application/json'
  });
  
  URL = "http://localhost:8084/"
  login()
  {    

    this.httpClient.get(this.URL + 'login').subscribe(()=>{})
  }
}
