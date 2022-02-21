import { HttpEvent } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
  fileId:number = 1;
  @Input()
  visible = false;
  @Input()
  autodownload = false;
  @Input()
  saveFile = false;
  @Output()
  blobEmitter: EventEmitter<Blob> = new EventEmitter();
  @Output()
  errorEmitter: EventEmitter<Error> = new EventEmitter();

  ngOnInit(): void
  {
    if(this.autodownload)
    {
      this.downloadFile(this.fileId)
    }
  }
  downloadFile(fileId: number)
  {
    this.fileHandler.downloadFile(fileId, this.saveFile).subscribe(blob=>{
      this.blobEmitter.emit(blob);
    }, error => {
      this.errorEmitter.emit(error);
    });
  }
}