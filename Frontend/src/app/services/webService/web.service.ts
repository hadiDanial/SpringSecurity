import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from 'src/app/models/entities/User';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { AuthService } from '../authService/auth.service';
import { DataService } from '../dataService/data.service';

@Injectable({
  providedIn: 'root'
})
export class WebService
{
  readonly ROOT_URL = "http://localhost:8084/"

  constructor(private httpClient: HttpClient, private dataService: DataService) { }
  token: string = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2huIiwiZXhwIjoxNjM0MDY5ODkxLCJpYXQiOjE2MzQwNjgwOTEsImlzcyI6IkhhZGkifQ.-dNwCDnuT7gmM_Ksx504w0DiM_YC1Ik96k8BnZDz-fsQzV41tRPD-IsQGlSLoPTZ0Jv29vlGo8ceqsiCKHItRQ";


  public post<T>(apiRoute: string, bodyParams?: Map<string, any>, headerParams?: Map<string, any>, urlParams?:Map<string, string>) : Observable<T>
  {
    let urlParamsString = (urlParams == undefined) ? "" : this.generateURLParams(urlParams);
    let options = {
      headers: this.generateHttpHeaders(),
      options: null
    };
    return this.httpClient.post<T>(`${this.ROOT_URL + apiRoute + urlParamsString}`, bodyParams, options).pipe(catchError(this.handleError)); 
  }

  generateHttpHeaders(): HttpHeaders
  {
    let httpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      'Accept': 'application/json',
      'username':'John',
      'password':'MagicalDaddy69'
    });
    let token =  this.getAccessToken();
    if(token != "")
    {
      httpHeaders = httpHeaders.append("accessToken", token);
    } 
    return httpHeaders;
  }
  getAccessToken()
  {
    return this.token;
  }

  generateURLParams(urlParams: Map<string, string>)
  {
    let result = "?";
    for (let [key, value] of urlParams)
    {
      result += key + "=" + value + "&";
    }
    result = result.substring(0, result.length - 1);
    return result;
  }

  private handleError(error: HttpErrorResponse)
  {
    // Handle the HTTP error here
    return throwError('Something wrong happened, ' + error.message);
  }
}
