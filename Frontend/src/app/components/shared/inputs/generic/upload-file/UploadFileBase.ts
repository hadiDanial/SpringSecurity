import { HttpClient, HttpEvent } from "@angular/common/http";
import { Injector } from "@angular/core";
import { Observable, Subscription } from "rxjs";
import { finalize } from "rxjs/operators";
import { environment } from "src/environments/environment";

export abstract class FileHandlerBase<T>
{
    httpClient: HttpClient;
    controllerURL: string = "/";
    type: string = "";
    defaultFileName: string = "file";
    resetFunc: Function = ()=>{console.log("reset")};
    constructor(injector: Injector, resetFunc?: Function)
    {
        this.httpClient = injector.get(HttpClient);
        this.resetFunc = (resetFunc == undefined) ? this.resetFunc : resetFunc;
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

export class ImageFileHandler extends FileHandlerBase<HttpEvent<number>>
{
    constructor(injector: Injector, resetFunc?: Function)
    {
        super(injector, resetFunc)
        this.controllerURL = "images/";
        this.type = "image/*"
        this.defaultFileName = "image.png"
    }
    uploadFile(): Observable<HttpEvent<number>>
    {
        return this.httpClient.post<number>(environment.baseURL + this.controllerURL + "uploadImage", this.formData,
            { reportProgress: true, observe: 'events' }).pipe(finalize(() => this.resetFunc()));
    }

}
