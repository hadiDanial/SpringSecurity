import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebService } from './../webService/web.service';
import { LoginResponse } from 'src/app/models/responses/LoginResponse';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { User } from 'src/app/models/entities/User';
import { AuthService } from '../authService/auth.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private webService: WebService) { }
  user : User = User.getDefaultUser();
  private userSource = new BehaviorSubject<User>(this.user);
  currentUser = this.userSource.asObservable();

  changeUser(user:User)
  {
    this.user = user;
    this.userSource.next(user);
  }

  getAllUsers()
  {
    this.webService.get<User[]>("user/getAllUsers", new Map<string, any>()).subscribe((users)=>{console.log(users)})
  }

}
