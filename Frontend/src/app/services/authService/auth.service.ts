import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/entities/User';
import { Name } from 'src/app/models/other/Name';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { LoginResponse } from 'src/app/models/responses/LoginResponse';
import { AlertService } from '../alertService/alert.service';
import { UserService } from '../userService/user.service';
import { WebService } from '../webService/web.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  readonly PATH_URL = "auth";
  constructor(private webService: WebService, private userService: UserService, private alertService:AlertService) { }
  loginResponse : LoginResponse | undefined;
  
  public login(loginRequest: LoginRequest)
  {
    // if(this.loginResponse != undefined)
    //   return;
    localStorage.removeItem("token");
    let map = new Map<string, any>();
    let message: string = "";
    map.set("username", loginRequest.username);
    map.set("password", loginRequest.password);
    let obs = this.webService.post<LoginResponse>(this.PATH_URL + "/login",undefined, map);
    this.alertService.loadingMenu<LoginResponse,HttpErrorResponse>("Logging in...",obs,(response:LoginResponse)=>
    {
      console.log("Login message: " + response.message);
        this.loginResponse = response;
        message = response.message;
        console.log(message);
        let user = response.user;
        let newUser = this.dataToUser(user);
        this.userService.changeUser(newUser);
        localStorage.setItem("token",response.token.accessToken);
        localStorage.setItem("refreshToken", response.token.refreshToken);
        localStorage.setItem("tokenExpiration", response.token.expiresAt+'');
    }, "Logged in succeessfully.",
    (error:HttpErrorResponse)=>{
      let httpError : (HttpErrorResponse)  = error;
      let headers = httpError.headers;
      console.log("Status: " + httpError.status);
      this.userService.changeUser(User.getDefaultUser());
      // TODO: Alert, add generic error handler
    }, "Failed to log in, try again.")
    }

    /**
     * Get the user data from the server by using the token.
     */
    getUserByToken()
    {
      this.webService.get<User>(this.PATH_URL+"/getUserByToken").subscribe(user=>{
        let newUser = this.dataToUser(user);
        this.userService.changeUser(newUser);
      }, error=>
      {
        alert("Failed to get user by token, clearing storage");
        this.removeTokenDataFromStorage();
      });
    }
  

  public logout()
  {
    let obs = this.webService.post<void>(this.PATH_URL+"/logout")
    this.alertService.loadingMenu("Logging out...",obs,()=>{
      this.removeTokenDataFromStorage();
      
    },"Logged out successfully", ()=>  { },//this.removeTokenDataFromStorage();  // Probably a bad idea to delete locally without deleting from the server as well...
    "Failed to log out, please try again.");
  }
  
  private removeTokenDataFromStorage()
  {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("tokenExpiration");
    this.userService.changeUser(User.getDefaultUser());
  }

  private dataToUser(user: User)
  {
    return new User(user.id, user.username, user.email, new Name(user.name.firstName, user.name.lastName, user.name.middleName));
  }

    /**
     * @returns returns the access token if it exists and hasn't expired yet. Deletes it if it has expired.
     */
    public getToken()
    {
      let token = localStorage.getItem("token");
      if(token)
      {
        // TODO: Need to check if token has expired, and if so, acquire a new token from the server (as long as the refresh token is valid)
        let expiresAt :Date = new Date(localStorage.getItem("tokenExpiration")+"");
        if(expiresAt > new Date())
        {
          return token;
        }
        else
        {
          localStorage.removeItem("token");
          localStorage.removeItem("tokenExpiration");
        }
      }
      return null;
    }

    getRefreshToken()
    {
      let refreshToken = localStorage.getItem("refreshToken");
      // TODO: Need to check if token has expired, and if so, delete and force the user to relog
      return refreshToken;
    }
  }
