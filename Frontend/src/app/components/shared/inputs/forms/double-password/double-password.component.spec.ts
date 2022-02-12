import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoublePasswordComponent } from './double-password.component';

describe('DoublePasswordComponent', () => {
  let component: DoublePasswordComponent;
  let fixture: ComponentFixture<DoublePasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoublePasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoublePasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
