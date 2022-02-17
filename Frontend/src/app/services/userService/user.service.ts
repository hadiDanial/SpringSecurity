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

  changeUser(user: User)
  {
    this.user = user;
    this.userSource.next(user);
  }

  getAllUsers()
  {
    this.webService.get<User[]>("user/getAllUsers", new Map<string, any>()).subscribe((users) => { console.log(users) })
  }

  register(firstName: string, lastName: string, username: string, email: string, password: string)
  {
    let map = new Map<string, any>();
    map.set("username", username);
    map.set("password", password);
    map.set("email", email);
    map.set("firstName", firstName);
    map.set("lastName", lastName);
    this.webService.post<MessageBoolResponse>("user/register", map).subscribe(response =>
    {
      console.log(response.result + ": " + response.message);
    })
  }

}
