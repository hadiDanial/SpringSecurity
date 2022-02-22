import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AlertService } from 'src/app/services/alertService/alert.service';
import { PostService } from 'src/app/services/postService/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit
{

  constructor(private formBuilder: FormBuilder, private postService: PostService, private alertService: AlertService) { }
  form: FormGroup = new FormGroup({});
  minPostLen = this.postService.minPostLen;
  maxPostLen = this.postService.maxPostLen;
  minTitleLen = this.postService.minTitleLen;
  maxTitleLen = this.postService.maxTitleLen;
  ngOnInit(): void
  {
    this.form = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(this.minTitleLen), Validators.maxLength(this.maxTitleLen)]],
      text: ['', [Validators.required, Validators.minLength(this.minPostLen), Validators.maxLength(this.maxPostLen)]]
    });
  }

  onSubmit()
  {
    if (this.form.valid)
    {
      let obs = this.postService.publish(this.form.value['title'], this.form.value['text']);
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
