import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from 'src/app/models/User';
import { DataService } from '../dataService/data.service';

@Injectable({
  providedIn: 'root'
})
export class WebService
{
  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
    'Accept': 'application/json'
  });

  defaultConfig = {
    headers: this.httpHeaders,
    options: null
  };
  readonly ROOT_URL = "http://localhost:8084/"


  public get<T>(apiRoute: string, data: Map<string, any>): Observable<T>
  {
    // let params = this.insertParams(data);
    // let options = { params: params };
    // this.defaultConfig.options = options;

    let options = this.generateOptions(data);
    // console.log(options.headers);
    // console.log(options.options.params.toString());
    
    var headers_object = new HttpHeaders();
    headers_object.append('Content-Type', 'application/json');
    headers_object.append("Authorization", "Basic " + btoa("John:MagicalDaddy69"));
    
    const httpOptions = {
      headers: headers_object
    };
    return this.httpClient.get<T>(`${this.ROOT_URL + apiRoute}`, options).pipe(catchError(this.handleError));
  }

  generateOptions(data: Map<string, any>)
  {
    let headers = this.generateHeaders();
    let params = this.insertParams(data);
    let options = {
      headers: headers,
      options: {params: params}
    }
    return options;
  }

  generateHeaders(): HttpHeaders
  {
    let user = this.dataService.getActiveUser();
    let httpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      'Accept': 'application/json',
      'username': user.username,
      'password': user.password
    });
    return httpHeaders;
  }

  public insertParams(map: Map<string, any>)
  {
    let params = new HttpParams();
    const paramsJson = {};
    if (map != null)
    {
      for (let [key, value] of map)
      {
        // value = JSON.stringify(value);
        params = params.append(key, value);
        // paramsJson[key] = value;
      }
    }
    let user: User = this.dataService.getActiveUser();
    params.append("username", user.username);
    params.append("password", user.password);
    return params;
  }

  private handleError(error: HttpErrorResponse)
  {
    // Handle the HTTP error here
    return throwError('Something wrong happened, ' + error.message);
  }
}
