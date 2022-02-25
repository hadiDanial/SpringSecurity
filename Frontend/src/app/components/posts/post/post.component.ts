import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/entities/Post';
import { AlertService } from 'src/app/services/alertService/alert.service';
import { CommentService } from 'src/app/services/commentService/comment.service';
import { PostService } from 'src/app/services/postService/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit
{

  constructor(private router: Router, private postService: PostService, private commentService: CommentService, private alertService: AlertService) { }
  @Input()
  post: Post = Post.getEmptyPost();

  url: string | undefined = "";
  value: string | number = "";
  urlIsTitle: boolean = false;
  isLoading: boolean = true;
  hasComments = false;
  ngOnInit(): void
  {
    let split = this.router.url.split('/');
    this.url = split[split.length - 1];
    let id = Number(this.url);
    let obs;
    if (isNaN(id))
    {
      this.value = this.url;
      this.urlIsTitle = true;
      obs = this.postService.getPostByTitle(this.value);
    }
    else
    {
      this.value = id;
      this.urlIsTitle = false;
      obs = this.postService.getPostById(this.value);
    }
    console.log(this.value + ", is title: " + this.urlIsTitle);
    obs.subscribe(post =>
    {
      this.post = post;
      this.isLoading = false;
      this.hasComments = this.post.comments.length > 0;
    }, error =>
    {
      this.alertService.alertWithCallback("Failed to GET post", 1500, false, ()=>{
        this.router.navigateByUrl('');
      }, 'center', 'error');
    })
  }

}
