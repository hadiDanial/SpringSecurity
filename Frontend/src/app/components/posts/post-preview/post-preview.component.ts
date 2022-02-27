import { Component, Input, OnInit } from '@angular/core';
import { Content } from 'src/app/models/entities/Content';
import { Post } from 'src/app/models/entities/Post';
import { Comment } from 'src/app/models/entities/Comment';
import { User } from 'src/app/models/entities/User';

@Component({
  selector: 'app-post-preview',
  templateUrl: './post-preview.component.html',
  styleUrls: ['./post-preview.component.scss']
})
export class PostPreviewComponent implements OnInit {

  constructor() { }
  @Input()
  content: Content = Content.getEmptyContent();
  @Input()
  i: number = 0;
  @Input()
  user: User = User.getDefaultUser();
  postLink = "/posts/" + this.content.id;
  @Input()
  isComment: boolean = false;
  post: Post = Post.getEmptyPost();
  comment: Comment = Comment.getEmptyComment();
  ngOnInit(): void {
    if(this.isComment)
    {
      this.comment = this.content as Comment;
      this.postLink =  "/posts/" + this.comment.post.underscoredTitle;
    }
    else
    {
      this.post = this.content as Post;
      this.postLink =  "/posts/" + this.post.underscoredTitle;
    }
  }

}
