import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit
{

  constructor(private userService: UserService) { }

  ngOnInit(): void
  {
  }
  login()
  {
    this.userService.login();
  }
  getAllUsers()
  {
    this.userService.getAllUsers();
  }
}
