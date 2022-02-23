import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/entities/Post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  constructor() { }
  @Input()
  post: Post = Post.getEmptyPost();
  
  ngOnInit(): void {
  }

}
