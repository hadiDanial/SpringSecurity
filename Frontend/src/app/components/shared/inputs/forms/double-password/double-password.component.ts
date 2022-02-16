import { Component, Input } from '@angular/core';
import { ControlContainer, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { matchingPasswordsValidator } from './matchingPasswordsValidator';

@Component({
  selector: 'app-double-password',
  templateUrl: './double-password.component.html',
  styleUrls: ['./double-password.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class DoublePasswordComponent
{
  @Input()
  doublePasswordGroup: FormGroup = new FormGroup({});

  password1: string = "";
  password2: string = "";
  constructor() { }

  setPassword1(password: string)
  {
    this.password1 = password;
    this.validate();
  }
  setPassword2(password: string)
  {
    this.password2 = password;
    this.validate();
  }
  validate()
  {
    // We need to update the values of password1 and password2 in the validator function
    this.doublePasswordGroup.setValidators([matchingPasswordsValidator(this.password1, this.password2)]);
    this.doublePasswordGroup.updateValueAndValidity();
  }
  
  getControl(controlName: string) : FormControl
  {
    return this.doublePasswordGroup.get(controlName) as FormControl;
  }
}