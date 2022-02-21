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
  disallowedNameMessage:string = 'Name must only contain letters.'
  disallowedUsernameMessage:string = 'Username must start with a letter and only contain letters and numbers.'
  form = new FormGroup({});

  ngOnInit(): void
  {
    this.form = this.formBuilder.group(
      {
        fileUpload: ["", Validators.required],
        firstName: ["", [Validators.required, Validators.pattern(this.userService.allowedNamePattern), Validators.minLength(this.userService.minInputLen), Validators.maxLength(this.userService.maxInputLen)]],
        lastName: ["", [Validators.required, Validators.pattern(this.userService.allowedNamePattern), Validators.minLength(this.userService.minInputLen), Validators.maxLength(this.userService.maxInputLen)]],
        username: ["", {validators:[Validators.required, Validators.pattern(this.userService.allowedUsernamePattern), Validators.minLength(this.userService.minUsernameLen), Validators.maxLength(this.userService.maxUsernameLen)],
                       asyncValidators:[ExistingValueValidator.checkExistingAsyncValidator("user/register/exists", "username", this.webService)], updateOn:'blur'}],
        email: ["", [Validators.required, Validators.email]],
        doublePasswordGroup: this.formBuilder.group(
          {
            password: ["", [Validators.required, Validators.pattern(this.userService.passwordPattern), Validators.minLength(this.userService.minPassLen), Validators.maxLength(this.userService.maxPassLen)]],
            confirmPassword: ["", [Validators.required, Validators.pattern(this.userService.passwordPattern), Validators.minLength(this.userService.minPassLen), Validators.maxLength(this.userService.maxPassLen)]]
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
