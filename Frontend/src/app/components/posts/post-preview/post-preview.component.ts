import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/entities/Post';
import { User } from 'src/app/models/entities/User';

@Component({
  selector: 'app-post-preview',
  templateUrl: './post-preview.component.html',
  styleUrls: ['./post-preview.component.scss']
})
export class PostPreviewComponent implements OnInit {

  constructor() { }
  @Input()
  post: Post = Post.getEmptyPost();
  @Input()
  i: number = 0;
  @Input()
  user: User = User.getDefaultUser();
  postLink = "/posts/" + this.post.id;
  ngOnInit(): void {
    this.postLink =  "/posts/" + this.post.underscoredTitle;
  }

}
