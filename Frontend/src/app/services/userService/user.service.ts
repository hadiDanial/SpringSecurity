import { Injectable } from '@angular/core';
import { WebService } from './../webService/web.service';
import { User } from 'src/app/models/entities/User';
import { BehaviorSubject, Observable } from 'rxjs';
import { MessageBoolResponse } from 'src/app/models/responses/MessageBoolResponse';
import { Name } from 'src/app/models/other/Name';

@Injectable({
  providedIn: 'root'
})
export class UserService
{

  constructor(private webService: WebService) { }
  user: User = User.getDefaultUser();
  private userSource = new BehaviorSubject<User>(this.user);
  currentUser = this.userSource.asObservable();
  minInputLen: number = 2;
  maxInputLen: number = 30;
  minUsernameLen: number = 3;
  maxUsernameLen: number = 20;
  minPassLen: number = 6;
  maxPassLen: number = 20;
  allowedNamePattern: string = '[a-zA-Z ]*';
  passwordPattern = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(.+?[#?!@$%^&*-]*).{' + (this.minPassLen-1) + ',' + this.maxPassLen + '}$';
  allowedUsernamePattern: string = '^[a-zA-Z][a-zA-Z0-9]*$';

  changeUser(user: User)
  {
    this.user = user;
    this.userSource.next(user);
  }

  getAllUsers()
  {
    this.webService.get<User[]>("user/getAllUsers", new Map<string, any>()).subscribe((users) => { console.log(users) })
  }

  setUserAboutMe(text:string) : Observable<MessageBoolResponse>
  {
    let map = new Map<string,string>();
    map.set("about", text);
    return this.webService.post<MessageBoolResponse>("user/setAboutMe", map);
  }

  register(firstName: string, lastName: string, username: string, email: string, password: string) : Observable<MessageBoolResponse>
  {
    let map = new Map<string, any>();
    map.set("username", username);
    map.set("password", password);
    map.set("email", email);
    map.set("firstName", firstName);
    map.set("lastName", lastName);
    return this.webService.post<MessageBoolResponse>("user/register", map);
  }

}
