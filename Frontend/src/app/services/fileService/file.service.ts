import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { ImageFileHandler } from 'src/app/components/shared/inputs/generic/upload-file/UploadFileBase';
import { environment } from 'src/environments/environment';
import { WebService } from '../webService/web.service';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  
  constructor(private httpClient:HttpClient, private webService: WebService, private injector: Injector) { }
  
  public imageHandler = new ImageFileHandler(this.injector);

}
