import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.scss']
})
export class UserSignupComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }
  firstName = "";
  lastName = "";
  email = "";
  minInputLen : number = 2;
  maxInputLen : number = 30;
  minPassLen : number = 2;
  maxPassLen : number = 30;
  allowedPattern: string = '[a-zA-Z ]*';
  passwordPattern = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(.+?[#?!@$%^&*-]*).{'+this.minPassLen+','+this.maxPassLen+'}$';
  form = new FormGroup({});
  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        firstName: ["", [Validators.required, Validators.pattern(this.allowedPattern),Validators.minLength(this.minInputLen), Validators.maxLength(this.maxInputLen)]],
        lastName: ["", [Validators.required, Validators.pattern(this.allowedPattern),Validators.minLength(this.minInputLen), Validators.maxLength(this.maxInputLen)]],
        email: ["", [Validators.required, Validators.email]],
        doublePasswordGroup: this.formBuilder.group(
          {
            password:["",[Validators.required, Validators.pattern(this.passwordPattern),Validators.minLength(this.minPassLen), Validators.maxLength(this.maxPassLen)]],
            confirmPassword:["",[Validators.required, Validators.pattern(this.passwordPattern),Validators.minLength(this.minPassLen), Validators.maxLength(this.maxPassLen)]]
          }
        )
      }
    )
  }
  onSubmit()
  {
    if(this.form.valid)
    {
      console.log("SUBMIT");
      let firstName = this.form.value["firstName"];
      let lastName = this.form.value["lastName"];
      let email = this.form.value["email"];
      let password = this.getGroupControl("doublePasswordGroup").value["password"];
      console.log(firstName + " " + lastName + " - " + email + ", " + password);
    }
  }
  getFormControl(controlName: string) : FormControl
  {
    return this.form.get(controlName) as FormControl;
  }
  getGroupControl(controlName: string) : FormGroup
  {
    return this.form.get(controlName) as FormGroup;
  }
}
