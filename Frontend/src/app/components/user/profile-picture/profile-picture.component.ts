import { Component, Input, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/fileService/file.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.scss']
})
export class ProfilePictureComponent implements OnInit
{

  constructor(private fileService: FileService, private sanitizer: DomSanitizer) { }
  @Input()
  userId: number = 0;
  blobData: Blob = new Blob();
  hasError = false;
  loading = true;
  profilePicHandler = this.fileService.profileImageHandler;
  @Input()
  size: string = "100";
  @Input()
  borderRadius = "border-radius: 50%;"
  @Input()
  borderWidth = "border-width: 5px;"
  @Input()
  borderStyle = "border-style: solid;"
  @Input()
  fit = "object-fit: cover;"
  width = "width: " + this.size + "px; height: " + this.size + "px;";
  style = "";
  imageSrc: any;

  ngOnInit(): void
  {
    this.style = this.fit + this.width + this.borderRadius + this.borderWidth + this.borderStyle;
  }
  onGetImage(blob: Blob)
  {
    this.blobData = blob;
    this.loading = false;
    this.createImageFromBlob(blob);
  }

  onError()
  {
    this.hasError = true;
    this.loading = false;
  }


  createImageFromBlob(image: Blob)
  {
    let objectURL = URL.createObjectURL(image);
    this.imageSrc = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  }
}
