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
  fileId: number = 1;
  @Input()
  visible = false;
  @Input()
  autodownload = false;
  @Input()
  waitBeforeDownload = true;
  @Input()
  saveFile = false;
  @Output()
  blobEmitter: EventEmitter<Blob> = new EventEmitter();
  @Output()
  errorEmitter: EventEmitter<Error> = new EventEmitter();

  ngOnInit(): void
  {
    if (this.autodownload)
    {
      setTimeout(() =>
      {
        this.downloadFile();
      }, this.waitBeforeDownload ? 5 : 0);
    }
  }
  downloadFile()
  {
    this.fileHandler.downloadFile(this.fileId, this.saveFile).subscribe(blob =>
    {
      this.blobEmitter.emit(blob);
    }, error =>
    {
      this.errorEmitter.emit(error);
    });
  }

  downloadFileWithId(id:number)
  {
    this.fileId = id;
    this.downloadFile();
  }
}