import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WebService } from '../webService/web.service';
import { Comment } from 'src/app/models/entities/Comment';
@Injectable({
  providedIn: 'root'
})
export class CommentService
{
  getPostComments(value: number): Observable<Comment[]>
  {
    return this.webService.get<Comment[]>("comments/getPostComments/" + value);
  }
  minCommentLen: number = 5;
  maxCommentLen: number = 500;
  minTitleLen: number = 0;
  maxTitleLen: number = 100;

  constructor(private webService: WebService) { }

  getUserComments(id: number): Observable<Comment[]>
  {
    return this.webService.get<Comment[]>("comments/getUserComments/" + id);
  }

  publish(title: string, text: string, postId: number): Observable<boolean>
  {
    let map = new Map<string, any>();
    map.set("title", title);
    map.set("text", text);
    map.set("postId", postId);
    return this.webService.post<boolean>("comments/addCommentToPost", map);
  }
}
