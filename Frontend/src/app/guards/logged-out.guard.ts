import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/authService/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoggedOutGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      console.log("GUARD")
      let isLoggedIn = this.authService.isLoggedIn();
      if(route.url[0].path === "login")
      {
        this.router.navigateByUrl('');
      }
      return !isLoggedIn;
    }
  
}
