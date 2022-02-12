import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/entities/User';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { AuthService } from 'src/app/services/authService/auth.service';
import { UserService } from 'src/app/services/userService/user.service';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit, OnDestroy
{
  password:string="";
  username:string="";
  isLoading = false;
  isLoggedIn = false;
  triedToLogin = false;
  defaultMessage = "Please enter your username and password to sign in.";
  successfulLoginMessage = "Signed in successfully. Welcome back, ";
  failedLoginMessage = "Failed to sign in. Please try again.";
  currentMessage = "";
  userSubscription: Subscription = new Subscription();
  currentUser : User = this.userService.user;
  firstName = "";
  lastName = "";
  email = "";
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  
  constructor(private userService: UserService, private authService:AuthService, private router: Router) { }


  ngOnInit(): void
  {
    this.currentMessage = this.defaultMessage;
    this.userSubscription = this.userService.currentUser.subscribe(currentUser => 
      {
        this.currentUser = currentUser;
        let equals = this.currentUser.equals(User.getDefaultUser());
        this.isLoggedIn = (!currentUser.equals(User.getDefaultUser()));
        if(!this.isLoggedIn && this.triedToLogin)
        {
          if(equals) // Error
          {               
            this.currentMessage = this.failedLoginMessage;        
          }
          else
          {
            this.currentMessage = this.successfulLoginMessage + this.currentUser.getName() + "!";
            this.isLoggedIn = true;
            this.password="";
            this.router.navigate(['/home']);
            // TODO: Redirect to home
          }

        }
        else
        {
          if(!equals)
          {
            console.log("Not equals");
          }
        }
      })
  }
  ngOnDestroy(): void
  {
    this.userSubscription.unsubscribe();
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
    let req = new LoginRequest(this.username, this.password);
    this.triedToLogin = true;
    this.authService.login(req);
  }

  logout()
  {
    this.authService.logout();
  }

  getAllUsers()
  {
    console.log("Name: " + this.firstName + " " + this.lastName + ", E-mail: " + this.email);

    this.userService.getAllUsers();
  }
}
