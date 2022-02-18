import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageBoolResponse } from 'src/app/models/responses/MessageBoolResponse';
import { AlertService } from 'src/app/services/alertService/alert.service';
import { UserService } from 'src/app/services/userService/user.service';
import { WebService } from 'src/app/services/webService/web.service';
import { ExistingValueValidator } from '../../shared/inputs/forms/checkExistingAsyncValidator';
@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.scss']
})
export class UserSignupComponent implements OnInit
{

  constructor(private formBuilder: FormBuilder, private userService: UserService, 
              private webService: WebService, private alertService:AlertService, private router:Router) { }

  firstName = "";
  lastName = "";
  email = "";
  minInputLen: number = 2;
  maxInputLen: number = 30;
  minUsernameLen: number = 3;
  maxUsernameLen: number = 20;
  minPassLen: number = 6;
  maxPassLen: number = 20;
  allowedNamePattern: string = '[a-zA-Z ]*';
  disallowedNameMessage:string = 'Name must only contain letters.'
  allowedUsernamePattern: string = '^[a-zA-Z][a-zA-Z0-9]*$';
  disallowedUsernameMessage:string = 'Username must start with a letter and only contain letters and numbers.'
  passwordPattern = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(.+?[#?!@$%^&*-]*).{' + (this.minPassLen-1) + ',' + this.maxPassLen + '}$';
  form = new FormGroup({});
  ngOnInit(): void
  {
    this.form = this.formBuilder.group(
      {
        firstName: ["", [Validators.required, Validators.pattern(this.allowedNamePattern), Validators.minLength(this.minInputLen), Validators.maxLength(this.maxInputLen)]],
        lastName: ["", [Validators.required, Validators.pattern(this.allowedNamePattern), Validators.minLength(this.minInputLen), Validators.maxLength(this.maxInputLen)]],
        username: ["", {validators:[Validators.required, Validators.pattern(this.allowedUsernamePattern), Validators.minLength(this.minUsernameLen), Validators.maxLength(this.maxUsernameLen)],
                       asyncValidators:[ExistingValueValidator.checkExistingAsyncValidator("user/register/exists", "username", this.webService)], updateOn:'blur'}],
        email: ["", [Validators.required, Validators.email]],
        doublePasswordGroup: this.formBuilder.group(
          {
            password: ["", [Validators.required, Validators.pattern(this.passwordPattern), Validators.minLength(this.minPassLen), Validators.maxLength(this.maxPassLen)]],
            confirmPassword: ["", [Validators.required, Validators.pattern(this.passwordPattern), Validators.minLength(this.minPassLen), Validators.maxLength(this.maxPassLen)]]
          }
        )
      }
    )
  }
  onSubmit()
  {
    if (this.form.valid)
    {
      console.log("SUBMIT");
      let firstName = this.form.value["firstName"];
      let lastName = this.form.value["lastName"];
      let username = this.form.value["username"];
      let email = this.form.value["email"];
      let password = this.getGroupControl("doublePasswordGroup").value["password"];

      let obs = this.userService.register(firstName, lastName, username, email, password);
      let res:MessageBoolResponse = new MessageBoolResponse("",false);
      this.alertService.loadingMenu("Signing up...", obs, (response:MessageBoolResponse) =>
      {
        res = response;
        console.log(response.result + ": " + response.message);
        if(res.result)
        {
          this.alertService.alertWithCallback(res.message,3000,false,()=>{
            this.router.navigateByUrl('/signup-success');
          },'center','success');
        }
      }, "", (error: HttpErrorResponse)=>{
        this.alertService.alert("Sign up failed, error " + error.status, 2500, false, 'center', 'error');
      }, ""),false}
  }
  getFormControl(controlName: string): FormControl
  {
    return this.form.get(controlName) as FormControl;
  }
  getGroupControl(controlName: string): FormGroup
  {
    return this.form.get(controlName) as FormGroup;
  }
}
