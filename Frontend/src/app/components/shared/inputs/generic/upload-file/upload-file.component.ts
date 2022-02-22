import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, EventEmitter, Injector, Input, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { FileService } from 'src/app/services/fileService/file.service';
import { maxFileSizeValidator } from './maxFileSizeValidator';
import { ImageFileHandler, FileHandlerBase } from './UploadFileBase';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.scss']
})
export class UploadFileComponent implements OnInit
{
  constructor(private fileService: FileService, private injector: Injector) { }
  @Input()
  uploadId: number = 1;
  @Input()
  autoUpload: boolean = false;
  @Input()
  formDataName = "multipartImage";
  @Input()
  acceptsImages = true;
  @Input()
  acceptsAudio = false;
  @Input()
  acceptsVideo = false;
  @Input()
  showUploadProgress = false;
  @Input()
  showMessage = false;
  @Input()
  fileExtensions = "";
  @Input()
  defaultMessage = "Select a file to upload.";
  @Input()
  maxFileSizeKB: number = 2048;
  @Input()
  maxSizeMessage: string = "Max File Size " + (this.maxFileSizeKB / 1024) + "MB";
  @Input()
  control = new FormControl();
  @Input()
  fileHandler: FileHandlerBase<HttpEvent<number>> = new ImageFileHandler(this.injector, "images/", this.reset);;
  fileName: string = "";
  accepts: string = "";
  uploadProgress: number = 0;
  uploadSub: Subscription = new Subscription();
  @Output()
  onUploadStart: EventEmitter<boolean> = new EventEmitter();
  @Output()
  onUploadComplete: EventEmitter<boolean> = new EventEmitter();
  ngOnInit(): void
  {
    this.generateAcceptsString();
  }
  private generateAcceptsString()
  {
    let fileFilter: string[] = [];
    this.accepts = "";
    if (this.acceptsImages)
      fileFilter.push("image/*");
    if (this.acceptsVideo)
      fileFilter.push("video/*");
    if (this.acceptsAudio)
      fileFilter.push("audio/*");
    for (let i = 0; i < fileFilter.length; i++)
    {
      this.accepts += fileFilter[i] + ",";
    }
    if (this.fileExtensions === "")
      this.accepts = this.accepts.slice(0, this.accepts.length - 1);
    else
      this.accepts += this.fileExtensions;
  }

  onFileSelected(event: any)
  {
    const fileList: FileList = event.target.files;
    const file = fileList.item(0);
    if (file)
    {
      this.control.setValidators([Validators.required, maxFileSizeValidator(this.maxFileSizeKB, fileList)])
      this.control.updateValueAndValidity();
      this.fileName = file.name;
      const formData = new FormData();
      formData.append(this.formDataName, file);
      //this.fileService.uploadFile(this.uploadFunction);
      this.fileHandler.OnFileSelected(formData);
      if (this.autoUpload)
      {
        this.onUploadStart.emit(true);
        this.fileHandler.uploadFile(this.uploadId).subscribe((event: HttpEvent<number>) =>
        {
          if (event.type == HttpEventType.UploadProgress && event.total != undefined)
          {
            this.uploadProgress = Math.round(100 * (event.loaded / event.total));
            if(this.uploadProgress == 100)
            {
              this.onUploadComplete.emit(true);
            }
            console.log(this.uploadProgress);
          }
        });
      }
    }
  }

  cancelUpload()
  {
    this.uploadSub.unsubscribe();
    this.reset();
  }

  reset()
  {
    this.uploadProgress = 0;
    this.uploadSub = new Subscription();
  }
}
