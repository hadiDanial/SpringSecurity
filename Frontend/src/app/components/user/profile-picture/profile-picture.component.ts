import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { FileService } from 'src/app/services/fileService/file.service';
import { DomSanitizer } from '@angular/platform-browser';
import { DownloadFileComponent } from 'src/app/components/shared/inputs/generic/download-file/download-file.component'
@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.scss']
})
export class ProfilePictureComponent implements OnInit, AfterViewInit, OnChanges
{
  constructor(private fileService: FileService, private sanitizer: DomSanitizer) { }

  @ViewChild(DownloadFileComponent)
  downloadFileComponent!: DownloadFileComponent;

  @Input()
  userId: number = 0;
  blobData: Blob = new Blob();
  hasError = false;
  loading = true;
  profilePicHandler = this.fileService.profileImageHandler;
  @Input()
  size: number = 100;
  @Input()
  showUploadButton: boolean = false;
  uploadStyle: string = "position: absolute;  transform: translate(-35px, 50px);"
  @Input()
  useBorder: boolean = false;
  @Input()
  borderRadius = "border-radius: 50%;"
  @Input()
  borderWidth = "border-width: 5px;"
  @Input()
  borderStyle = "border-style: ridge;"
  @Input()
  fit = "object-fit: cover;"
  width = "width: " + this.size + "px; height: " + this.size + "px;";
  style = "";
  imageSrc: any;

  ngOnInit(): void
  {
    this.style = this.fit + this.width + this.borderRadius + ((this.useBorder) ? this.borderWidth + this.borderStyle : "");
  }
  ngOnChanges(changes: SimpleChanges): void
  {
    this.getPicture();
  }

  ngAfterViewInit(): void
  {
    this.getPicture();
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

  onUploadStart()
  {
    this.loading = true;
  }

  onUploadComplete()
  {
    this.getPicture();
  }
  private getPicture()
  {
    if (this.downloadFileComponent != undefined && this.userId != undefined && this.userId != 0)
    {
      this.downloadFileComponent.downloadFileWithId(this.userId);
    }
  }
}
