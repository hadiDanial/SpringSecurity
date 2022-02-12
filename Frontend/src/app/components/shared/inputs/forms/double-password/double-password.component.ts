import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ControlContainer, FormControl, NgForm, Validators } from '@angular/forms';
import { matchingPasswordsValidator } from './matchingPasswordsValidator';

@Component({
  selector: 'app-double-password',
  templateUrl: './double-password.component.html',
  styleUrls: ['./double-password.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class DoublePasswordComponent implements OnChanges
{

  password1: string = "";
  password2: string = "";
  inputFormControl = new FormControl('', [matchingPasswordsValidator(this.password1, this.password2)]);

  constructor() { }
  ngOnChanges(changes: SimpleChanges): void
  {
  }

  setPassword1(password: string)
  {
    this.password1 = password;
    console.log("PW1: " + password);
    this.validate();
  }
  setPassword2(password: string)
  {
    this.password2 = password;
    console.log("PW2: " + password);
    this.validate();
  }
  validate()
  {
    this.inputFormControl.setValidators([matchingPasswordsValidator(this.password1, this.password2)]);
    this.inputFormControl.updateValueAndValidity();
  }
}