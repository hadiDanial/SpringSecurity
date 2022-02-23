import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'postPreview'
})
export class PostPreviewPipe implements PipeTransform
{

  maxLength = 100;
  transform(value: string, ...args: unknown[]): string
  {
    if (value.length <= this.maxLength)
      return value;
    let text: string = value.slice(0, this.maxLength);
    text = text.slice(0, text.lastIndexOf(" ")) + "...";
    return text;
  }

}
