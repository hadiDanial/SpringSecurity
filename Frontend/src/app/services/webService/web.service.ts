import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { DataService } from '../dataService/data.service';

@Injectable({
  providedIn: 'root'
})
export class WebService
{
  readonly ROOT_URL = "http://localhost:8084/"

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  public get<T>(apiRoute: string, headerParams?: Map<string, string>, urlParams?:Map<string, string>) : Observable<T>
  {
    let urlParamsString = (urlParams == undefined) ? "" : this.generateURLParams(urlParams);
    let options = {
      headers: this.generateHttpHeadersWithParams(headerParams),
      options: null,
      
    };
    return this.httpClient.get<T>(`${this.ROOT_URL + apiRoute + urlParamsString}`, options).pipe(catchError(this.handleError));
  }

  public post<T>(apiRoute: string, bodyParams?: Map<string, any>, headerParams?: Map<string, string>, urlParams?:Map<string, string>) : Observable<T>
  {
    let urlParamsString = (urlParams == undefined) ? "" : this.generateURLParams(urlParams);
    let options = {
      headers: this.generateHttpHeaders(headerParams),
      options: null
    };
    return this.httpClient.post<T>(`${this.ROOT_URL + apiRoute + urlParamsString}`, bodyParams, options);//.pipe(catchError(this.handleError)); 
  }

  generateHttpHeaders(headerParams?:Map<string, string>): HttpHeaders
  {
    let httpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      'Accept': 'application/json',
    });
    if(headerParams != undefined)
    {
      headerParams.forEach((value:string, key:string) => {
        httpHeaders = httpHeaders.append(key, value);        
      });
    }
    console.log(httpHeaders);
    //let token =  this.getAccessToken();
    // if(token != "")
    // {
    //   httpHeaders = httpHeaders.append("accessToken", token);
    // } 
    return httpHeaders;
  }

  generateHttpHeadersWithParams(params?: Map<string, string>): HttpHeaders
  {
    let httpHeaders = this.generateHttpHeaders();
    if(params == undefined)
    {
      return httpHeaders;
    }
    for (let [key, value] of params)
    {
      httpHeaders = httpHeaders.append(key, value);      
    }
    return httpHeaders;
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
