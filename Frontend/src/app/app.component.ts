import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from './models/entities/User';
import { AuthService } from './services/authService/auth.service';
import { UserService } from './services/userService/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy{
  userSubscription: Subscription = new Subscription();
  currentUser : User = this.userService.user;
  constructor(private userService: UserService, private authService: AuthService){}
  ngOnInit(): void
  {
    let token = this.authService.getToken();
    if(token)
    {
      this.authService.getUserByToken();
    }
    this.userSubscription = this.userService.currentUser.subscribe(currentUser => 
      {
        this.currentUser = currentUser;
      });
  }
  ngOnDestroy(): void
  {
    this.userSubscription.unsubscribe();
  }

  title = 'Frontend';
}
