import { Component, Input, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit
{
  password:string="";
  username:string="";
  constructor(private userService: UserService) { }

  ngOnInit(): void
  {
  }

  updateUsername(username: any)  
  {
    this.username = username.target.value;
  }
  updatePassword(password: any)  
  {
    this.password = password.target.value;
  }

  login()
  {
    console.log(this.username + " " + this.password);
    this.userService.login(this.username, this.password);
  }
  getAllUsers()
  {
    this.userService.getAllUsers();
  }
}
