import { Component, OnDestroy, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable, Subscription } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { UserService } from 'src/app/services/userService/user.service';
import { User } from 'src/app/models/entities/User';
import { AuthService } from 'src/app/services/authService/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, OnDestroy{
  userSubscription: Subscription = new Subscription();
  currentUser : User = User.getDefaultUser();
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService, private authService: AuthService) {}
  
  ngOnInit(): void
  {
    this.userSubscription = this.userService.currentUser.subscribe(currentUser => 
      {
        this.currentUser = currentUser;
      });
    }
    
    ngOnDestroy(): void
    {
      this.userSubscription.unsubscribe();
    }

    isLoggedIn(): boolean{
      return this.authService.isLoggedIn();
    }

}
