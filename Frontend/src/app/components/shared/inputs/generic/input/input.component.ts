import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})
export class InputComponent{

  @Input()
  inputName: string = "";
  @Input()
  placeholder: string = "Text";
  @Input()
  type: string = "text";
  @Input()
  patternErrorMessage: string = "Required pattern error.";
  @Input()
  readonly:boolean = false;
  @Input()
  min : number = 2;
  @Input()
  max : number = 30;



  value : string = "";
  @Input()
  control = new FormControl();

  constructor() { }

}
