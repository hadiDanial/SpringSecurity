import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Post } from 'src/app/models/entities/Post';
import { AlertService } from 'src/app/services/alertService/alert.service';
import { CommentService } from 'src/app/services/commentService/comment.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.scss']
})
export class AddCommentComponent implements OnInit {
  constructor(private formBuilder: FormBuilder, private commentService: CommentService, private alertService: AlertService) { }

  @Input()
  post: Post = Post.getEmptyPost();

  form: FormGroup = new FormGroup({});
  minCommentLen = this.commentService.minCommentLen;
  maxCommentLen = this.commentService.maxCommentLen;
  minTitleLen = this.commentService.minTitleLen;
  maxTitleLen = this.commentService.maxTitleLen;
  ngOnInit(): void
  {
    this.form = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(this.minTitleLen), Validators.maxLength(this.maxTitleLen)]],
      text: ['', [Validators.required, Validators.minLength(this.minCommentLen), Validators.maxLength(this.maxCommentLen)]]
    });
  }

  onSubmit()
  {
    if (this.form.valid)
    {
      let obs = this.commentService.publish(this.form.value['title'], this.form.value['text'], this.post.id);
      this.alertService.loadingMenu("Posting...", obs, (res: boolean) => { }, "Post published successfully.",
        () => { }, "Failed to publish post.");
    }
  }
  // savePost()
  // {
  //   this.postService.savePost()
  // }
  getFormControl(controlName: string): FormControl
  {
    return this.form.get(controlName) as FormControl;
  }

}
