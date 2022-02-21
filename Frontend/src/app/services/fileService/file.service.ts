import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { ImageFileHandler } from 'src/app/components/shared/inputs/generic/upload-file/UploadFileBase';
import { WebService } from '../webService/web.service';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  
  constructor(private injector: Injector) { }
  
  public imageHandler = new ImageFileHandler(this.injector, "images/");
  public profileImageHandler = new ImageFileHandler(this.injector, "images/profile-pictures/");

}
