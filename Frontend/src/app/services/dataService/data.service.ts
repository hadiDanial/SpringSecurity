import { Injectable } from '@angular/core';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }
  getActiveUser(): User
  {
    let user = new User("John", "MagicalDaddy69");
    return user;
  }
}
