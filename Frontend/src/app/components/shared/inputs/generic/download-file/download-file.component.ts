import { HttpEvent } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/fileService/file.service';
import { FileHandlerBase } from '../upload-file/UploadFileBase';

@Component({
  selector: 'app-download-file',
  templateUrl: './download-file.component.html',
  styleUrls: ['./download-file.component.scss']
})
export class DownloadFileComponent implements OnInit
{
  constructor(private fileService: FileService) { }

  @Input()
  fileHandler: FileHandlerBase<HttpEvent<number>> = this.fileService.imageHandler;
  @Input()
  visible = false;
  
  ngOnInit(): void
  {
  }
  downloadFile(fileId: number)
  {
    this.fileHandler.downloadFile(fileId);
  }
}