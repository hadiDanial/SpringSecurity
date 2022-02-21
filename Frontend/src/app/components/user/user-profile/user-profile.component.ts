import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/entities/User';
import { FileService } from 'src/app/services/fileService/file.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit
{
  user: User = this.userService.user;
  constructor(private userService: UserService, private fileService: FileService) { }
  profileHandler = this.fileService.profileImageHandler;

  ngOnInit(): void
  {
    this.userService.currentUser.subscribe(user =>
    {
      this.user = user;
    })
  }
  uploadPicture()
  {

  }
}
