import { Injectable } from '@angular/core';
import { User } from 'src/app/models/entities/User';
import { Name } from 'src/app/models/other/Name';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }
  getActiveUser(): User
  {
    let user = new User("John", "MagicalDaddy69", new Name("John", "Constantine"));
    return user;
  }
}
