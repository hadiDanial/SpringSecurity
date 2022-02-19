import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function maxFileSizeValidator(maxSizeInKB: number, fileList:FileList | undefined): ValidatorFn
{
    return (control: AbstractControl): ValidationErrors | null =>
    {
        const file = control.value;
        if (file && fileList!= undefined)
        {
            let size = fileList.item(0)?.size;
            if (size != null && Math.round(size / 1024) > maxSizeInKB)
            {
                return { fileSize: true }
            }
            else
                return null;
        }
        else
            return null;
    };
}