import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'app-text-area',
  templateUrl: './text-area.component.html',
  styleUrls: ['./text-area.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }]
})
export class TextAreaComponent{

  @Input()
  inputName: string = "";
  @Input()
  placeholder: string = "Text";
  @Input()
  readonly:boolean = false;
  @Input()
  min : number = 50;
  @Input()
  max : number = 1000;
  @Input()
  rows : number = 5;
  @Input()
  minHeight = "50px";
  @Input()
  required = true;

  value : string = "";
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();

  inputFormControl = new FormControl('', [Validators.minLength(this.min), Validators.maxLength(this.max)]);

}
