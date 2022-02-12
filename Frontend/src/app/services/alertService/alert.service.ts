import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import swal, { SweetAlertOptions } from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  
  constructor() { }

  confirmAlert(
    title: string,
    message: string,
    successMessage: string,
    successTitle: string,
    failureMessage: string,
    failureTitle: string,
    successCallback: Function,
    failureCallback: Function,
    confirmIcon: string = 'success',
    dismissFunction?: Function,
  ) 
  {
    const options = {
      title: title,
      text: message,
      icon: confirmIcon,
      showConfirmButton: true,
      showCancelButton: true,
      customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger',
            title: '',
          }
    } as SweetAlertOptions;

    swal.fire(options).then((result) =>
    {
      if (result.value)
      {
        successCallback();
        if (!(successTitle == "" && successMessage == ""))
        {
          const successOptions = {
            title : successTitle,
            text: successMessage,
            icon: 'success'
          } as SweetAlertOptions;
          swal.fire(successOptions);
        }
      }
      else if (result.dismiss === swal.DismissReason.cancel)
      {
        failureCallback();
        if (!(failureTitle == "" && failureMessage == ""))
        {
          const failureOptions = {
            title : failureTitle,
            text: failureMessage,
            icon: 'error'
          } as SweetAlertOptions;
          swal.fire(failureOptions);
        }
      } else
      {
        if (dismissFunction != undefined)
          dismissFunction();
      }
    });
  }

  /**
   * 
   * @param title 
   * @param timer 
   * @param position 'top', 'top-start', 'top-end' (default position), 'center', 'center-start', 'center-end', 'bottom', 'bottom-start', or 'bottom-end'.
   * @param icon 'success', 'error', 'info', 'warning', 'question'
   */
  alert(title: string, timer: number, toast: boolean, position: string = 'top-end', icon: string = 'success')
  {
    const options = {
      title: title,
      icon: icon,
      position: position,
      showConfirmButton: false,
      toast: toast,
      timer: timer
    } as SweetAlertOptions;
    
    swal.fire(options);
  }
  /**
   * 
   * @param title 
   * @param timer 
   * @param position 'top', 'top-start', 'top-end' (default position), 'center', 'center-start', 'center-end', 'bottom', 'bottom-start', or 'bottom-end'.
   * @param icon 'success', 'error', 'info', 'warning', 'question'
   */
   alertWithCallback(title: string, timer: number, toast: boolean, successCallback: Function, position: string = 'top-end', icon: string = 'success')
   {
     const options = {
       title: title,
       icon: icon,
       position: position,
       showConfirmButton: false,
       toast: toast,
       timer: timer
     } as SweetAlertOptions;
     
     swal.fire(options).then((result)=>
     {

         successCallback();
       
     });
   }
   loadingMenu(message: string, obs: Observable<boolean>, successCallback: Function, failureCallback: Function)
   {
     swal.fire({
       text: message,
       allowOutsideClick: false,
       showCloseButton: false,
       onBeforeOpen: () =>
       {
         swal.showLoading()
         obs.subscribe(() =>
         {
           successCallback();
         },
           () =>
           {
             failureCallback();
           })
       }
     } as SweetAlertOptions);
   }
 
 }