import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/entities/Post';

@Component({
  selector: 'app-post-preview',
  templateUrl: './post-preview.component.html',
  styleUrls: ['./post-preview.component.scss']
})
export class PostPreviewComponent implements OnInit {

  constructor() { }
  @Input()
  post: Post = Post.getEmptyPost();
  ngOnInit(): void {
  }

}
