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
  readonly:boolean = false;
  @Input()
  min : number = 2;
  @Input()
  max : number = 30;
  @Input()
  allowAllCharacters = false;
  @Input()
  required = true;
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();

  value : string = "";
  inputFormControl = new FormControl('', [Validators.pattern((this.allowAllCharacters?'.*':'[a-zA-Z ]*')),Validators.minLength(this.min), Validators.maxLength(this.max)]);

  constructor() { }
  onValueChanged(val:string)
  {
    this.value = val;
    this.onChange.emit(this.value);
  }
}
