import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
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
  constructor(private fileService:FileService) { }
  
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
  fileHandler:FileHandlerBase<number> = this.fileService.imageHandler;
  fileName: string = "";
  accepts: string = "";

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
      if(this.autoUpload)
        this.fileHandler.uploadFile().subscribe((res)=>{alert(res)});
    }
  }
}
