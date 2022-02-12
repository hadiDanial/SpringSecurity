import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ControlContainer, NgForm } from '@angular/forms';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss'],
  viewProviders: [{ provide: ControlContainer, useExisting: NgForm }],
})

export class EmailComponent implements OnChanges {
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  value: string = "";
  @Input()
  placeholder: string = "example@gmail.com"
  @Output()
  onChange : EventEmitter<string> = new EventEmitter();
  constructor() { }
  ngOnChanges(changes: SimpleChanges): void
  {
    if(this.emailFormControl.valid)
    {
      this.onChange.emit(this.value);
    }
  }


}
