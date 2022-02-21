import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/authService/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate
{
  constructor(private authService: AuthService, private router: Router) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
  {
    let isLoggedIn = this.authService.isLoggedIn();
    if(route.url[0].path === "login" || route.url[0].path === "signup")
    {
      this.router.navigateByUrl('');
    }
    return isLoggedIn;
  }

}
