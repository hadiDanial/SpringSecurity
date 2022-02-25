import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/entities/Post';
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
  getAllPosts(): Observable<Post[]>
  {
    return this.webService.get<Post[]>("posts/getAllPosts");
  }
  getUserPosts(id: number): Observable<Post[]>
  {
    return this.webService.get<Post[]>("posts/getUserPosts/"+id);
  }
  getPostById(value: number): Observable<Post>
  {
    return this.webService.get<Post>("posts/getPostById/" + value);
  }
  getPostByTitle(value: string): Observable<Post>
  {
    let map = new Map<string, string>();
    map.set("title", value);
    return this.webService.get<Post>("posts/getPostByTitle", undefined, map);
  }
}
