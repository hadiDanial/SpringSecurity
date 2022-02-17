import { AbstractControl, ValidationErrors, AsyncValidatorFn } from "@angular/forms";
import { Observable } from "rxjs";
import { Injectable } from '@angular/core';
import { WebService } from "src/app/services/webService/web.service";
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class ExistingValueValidator
{

    static checkExistingAsyncValidator(url: string, key: string, webService: WebService): AsyncValidatorFn
    {
        return (control: AbstractControl): Observable<ValidationErrors | null> =>
        {
            let valMap = new Map<string, string>();
            valMap.set(key, control.value);
            let obs = webService.get<boolean>(url, undefined, valMap);
            obs.subscribe();
            return obs.pipe(
                map((result: boolean) =>
                  result ? { keyAlreadyExists: true } : null
                )
              );

        };
    }
}