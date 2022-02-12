import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { ControlContainer, FormControl, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],

})
export class PasswordComponent{

  constructor() { }
  @Input()
  formControlName: string = "";

  @Input()
  inputName: string = "";
  @Input()
  placeholder: string = "Password";
  @Input()
  min : number = 6;
  @Input()
  max : number = 20;
  @Input()
  required = true;
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();
  isPassword = true;
  type = "password";
  value : string = "";
  hasClickedInside = false;
  inputFormControl = new FormControl('', [Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(.+?[#?!@$%^&*-]*).{'+this.min+','+this.max+'}$'),Validators.minLength(this.min), Validators.maxLength(this.max)]);


  @HostListener("document:click")
  clickedOut() {
    if(!this.hasClickedInside){
      
      this.isPassword = true;
      this.setInputType();
    }
    this.hasClickedInside=false;
  }
  @HostListener("click")
  clickedInside() {
    this.hasClickedInside=true;
  }
  onPasswordChanged(password:string){
    if(this.inputFormControl.valid)
    {
      this.value=password;
      this.onChange.emit(this.value);
    }
  }

  toggleInputType()
  {
    this.isPassword = !this.isPassword;
    this.setInputType();
  }

  private setInputType()
  {
    if (this.isPassword)
    {
      this.type = "password";
    }

    else
    {
      this.type = "text";
    }
  }
}
