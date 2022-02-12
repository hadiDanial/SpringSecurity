import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function matchingPasswordsValidator(password1: string, password2: string): ValidatorFn
{
  return (control: AbstractControl): ValidationErrors | null =>
  {
    if (password1 === password2)
      return null;
    else
      return { 'noMatch': true };
  };
}