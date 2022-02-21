import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/entities/User';
import { LoginRequest } from 'src/app/models/requests/LoginRequest';
import { AuthService } from 'src/app/services/authService/auth.service';
import { UserService } from 'src/app/services/userService/user.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit, OnDestroy
{
  isLoading = false;
  isLoggedIn = false;
  triedToLogin = false;
  defaultMessage = "Please enter your username and password to sign in.";
  successfulLoginMessage = "Signed in successfully. Welcome back, ";
  failedLoginMessage = "Failed to sign in. Please try again.";
  disallowedUsernameMessage:string = 'Username must start with a letter and only contain letters and numbers.'
  currentMessage = "";
  userSubscription: Subscription = new Subscription();
  currentUser: User = this.userService.user;
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  form = new FormGroup({});
  minUsernameLen: number = 3;
  maxUsernameLen: number = 20;
  constructor(private userService: UserService, private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }


  ngOnInit(): void
  {
    this.form = this.formBuilder.group(
      {
        username: ["", { validators: [Validators.required, Validators.pattern(this.userService.allowedUsernamePattern),
                                      Validators.minLength(this.minUsernameLen), Validators.maxLength(this.maxUsernameLen)] }],
        password: ["", [Validators.required,  Validators.minLength(this.userService.minPassLen), 
                        Validators.maxLength(this.userService.maxPassLen)]] //Validators.pattern(this.userService.passwordPattern),
      })
    this.currentMessage = this.defaultMessage;
    this.userSubscription = this.userService.currentUser.subscribe(currentUser => 
    {
      this.currentUser = currentUser;
      let equals = this.currentUser.equals(User.getDefaultUser());
      this.isLoggedIn = (!currentUser.equals(User.getDefaultUser()));
      if (!this.isLoggedIn && this.triedToLogin)
      {
        if (equals) // Error
        {
          this.currentMessage = this.failedLoginMessage;
        }
        else
        {
          this.currentMessage = this.successfulLoginMessage + this.currentUser.getName() + "!";
          this.isLoggedIn = true;
          this.router.navigate(['/home']);
          // TODO: Redirect to home
        }

      }
    })
  }
  ngOnDestroy(): void
  {
    this.userSubscription.unsubscribe();
  }

  onSubmit()
  {
    if(this.form.valid)
    {
      let req = new LoginRequest(this.form.value["username"],this.form.value["password"]);
      this.triedToLogin = true;
      this.authService.login(req);
    }
  }
  
  getFormControl(controlName: string): FormControl
  {
    return this.form.get(controlName) as FormControl;
  }
}
