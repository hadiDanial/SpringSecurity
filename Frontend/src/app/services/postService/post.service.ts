import { Injectable } from '@angular/core';
import { WebService } from '../webService/web.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  minTitleLen: number = 3;
  maxTitleLen: number = 100;
  minPostLen: number = 100;
  maxPostLen: number = 2000;
  
  constructor(private webService: WebService) { }
  publish(title: string, text: string)
  {
    let map = new Map<string, string>();
    map.set("title", title);
    map.set("text", text);
    return this.webService.post<boolean>("posts/newPost", map);
  }
}
