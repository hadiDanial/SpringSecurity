import { Component, Input, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/fileService/file.service';

@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.scss']
})
export class ProfilePictureComponent implements OnInit {

  constructor(private fileService: FileService) { }
  @Input()
  userId: number = 1;
  blobData: Blob = new Blob();
  hasError = false;
  loading = true;
  profilePicHandler = this.fileService.profileImageHandler;
  ngOnInit(): void {
  }
  onGetImage(blob: Blob)
  {
    this.blobData = blob;
    this.loading = false;
  }
  
  onError()
  {
    this.hasError = true;
    this.loading = false;
  }
}
