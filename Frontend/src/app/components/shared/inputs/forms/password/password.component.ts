import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, ControlContainer, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],

})
export class PasswordComponent{

  constructor() { }
 
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
  @Input()
  control: FormControl =  new FormControl();
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();
  isPassword = true;
  type = "password";
  value : string = "";
  hasClickedInside = false;

  
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
    if(this.control.valid)
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
