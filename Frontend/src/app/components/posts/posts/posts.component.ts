import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/entities/Post';
import { PostService } from 'src/app/services/postService/post.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private postService: PostService) { }

  posts: Post[] = [];
  isLoading = true;
  ngOnInit(): void {
    this.postService.getAllPosts().subscribe(posts=>{
      this.posts = posts;
      this.isLoading = false;
    })
  }

}
