import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})

export class EmailComponent{
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  value: string = "";
  @Input()
  placeholder: string = "example@gmail.com"
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();
  constructor() { }

  
  onEmailChanged()
  {
    if(this.emailFormControl.valid)
    {
      this.value = this.emailFormControl.value;
      this.onChange.emit(this.value);
    }  
  }
}
