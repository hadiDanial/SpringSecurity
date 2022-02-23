import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Post } from 'src/app/models/entities/Post';
import { User } from 'src/app/models/entities/User';
import { MessageBoolResponse } from 'src/app/models/responses/MessageBoolResponse';
import { AlertService } from 'src/app/services/alertService/alert.service';
import { FileService } from 'src/app/services/fileService/file.service';
import { PostService } from 'src/app/services/postService/post.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit
{
  @Input()
  user: User = this.userService.user;
  constructor(private userService: UserService, private postService: PostService, private fileService: FileService, private alertService: AlertService) { }

  shouldGetPosts: boolean = true;
  profileHandler = this.fileService.profileImageHandler;
  isEditing: boolean = false;
  control = new FormControl('', Validators.required);

  ngOnInit(): void
  {
    this.getUserPosts();
    this.userService.currentUser.subscribe(user =>
    {
      this.user = user;
      if (this.shouldGetPosts)
        this.getUserPosts();
    })
  }
  getUserPosts()
  {
    if (this.user.id != 0)

      this.postService.getUserPosts(this.user.id).subscribe((posts: Post[]) =>
      {
        this.user.profile.posts = posts;
        this.shouldGetPosts = false;
        this.userService.changeUser(this.user);
      });
  }
  isLoggedInUser(): boolean
  {
    return this.user.equals(this.userService.user);
  }

  editAboutMe()
  {
    this.isEditing = true;
  }
  onSubmit()
  {
    if (this.isEditing && this.control.valid)
    {
      let obs = this.userService.setUserAboutMe(this.control.value);
      this.alertService.loadingMenu("Saving...", obs, (res: MessageBoolResponse) =>
      {
        // this.alertService.alert(res.message, 20000, false, 'center', 'success');
        this.user.profile.about = this.control.value;
        this.userService.changeUser(this.user);
        this.isEditing = false;
      }, "Updated about me successfully.", (err: MessageBoolResponse) =>
      {
        // this.alertService.alert(err.message, 20000, false, 'center', 'error');

      }, "Failed to update!");
    }
  }
}
