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
  @Input()
  control = new FormControl();
  value: string = "";
  @Input()
  placeholder: string = "example@gmail.com"

  constructor() { }

  
  // onEmailChanged()
  // {
  //   if(this.control.valid)
  //   {
  //     this.value = this.control.value;
  //     this.onChange.emit(this.value);
  //   }  
  // }
}
