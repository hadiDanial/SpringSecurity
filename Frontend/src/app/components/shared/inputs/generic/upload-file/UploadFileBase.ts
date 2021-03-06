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
    resetFunc: Function = () => { };
    constructor(injector: Injector, resetFunc?: Function)
    {
        this.httpClient = injector.get(HttpClient);
        this.resetFunc = (resetFunc == undefined) ? this.resetFunc : resetFunc;
    }
    formData: FormData = new FormData();
    OnFileSelected(formData: FormData) { this.formData = formData }
    abstract uploadFile(param?:number): Observable<T>;
    downloadFile(fileId: number, save: boolean = true) : Observable<Blob>
    {
        let obs: Observable<Blob> = new Observable(subscriber => {
            this.httpClient.get(environment.baseURL + this.controllerURL + fileId, { responseType: 'arraybuffer' }).subscribe((res) =>
            {
                var blob = new Blob([res], { type: this.type });
                if (save)
                {
                    var link = document.createElement('a');
                    link.href = window.URL.createObjectURL(blob);
                    link.download = this.defaultFileName;
                    link.click();
                    link.remove();
                }
                subscriber.next(blob);
            }, error => { 
                subscriber.error(error);
                console.log("Error! " + error);
             })

        });
        return obs;
    }

}

export class ImageFileHandler extends FileHandlerBase<HttpEvent<number>>
{
    constructor(injector: Injector, controllerURL: string, resetFunc?: Function)
    {
        super(injector, resetFunc)
        this.controllerURL = controllerURL;
        this.type = "image/*"
        this.defaultFileName = "image.png"
    }
    uploadFile(param?:number): Observable<HttpEvent<number>>
    {
        let mapping = "uploadImage";
        if(param != undefined)
        {
            mapping += "/" + param;
        }
        return this.httpClient.post<number>(environment.baseURL + this.controllerURL + mapping, this.formData,
            { reportProgress: true, observe: 'events' }).pipe(finalize(() => this.resetFunc()));
    }

}
