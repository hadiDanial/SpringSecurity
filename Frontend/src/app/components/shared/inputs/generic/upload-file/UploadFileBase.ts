import { HttpClient } from "@angular/common/http";
import { Injector } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

export abstract class FileHandlerBase<T>
{
    httpClient: HttpClient;
    controllerURL: string = "/";
    type: string = "";
    defaultFileName: string = "file";
    constructor(injector: Injector)
    {
        this.httpClient = injector.get(HttpClient);
    }
    formData: FormData = new FormData();
    OnFileSelected(formData: FormData) { this.formData = formData }
    abstract uploadFile(): Observable<T>;
    downloadFile(fileId: number) 
    {
        this.httpClient.get(environment.baseURL + this.controllerURL + fileId, { responseType: 'arraybuffer' }).subscribe((res) =>
        {
            var blob = new Blob([res], { type: this.type });
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = this.defaultFileName;
            link.click();
            link.remove();
        }, error => { console.log("Error! " + error) })
    }
}

export class ImageFileHandler extends FileHandlerBase<number>
{
    constructor(injector: Injector)
    {
        super(injector)
        this.controllerURL = "images/";
        this.type = "image/*"
        this.defaultFileName = "image.png"
    }
    uploadFile(): Observable<number>
    {
        return this.httpClient.post<number>(environment.baseURL + this.controllerURL + "uploadImage", this.formData);
    }

}
