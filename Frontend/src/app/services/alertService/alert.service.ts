import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Swal, { SweetAlertOptions } from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService
{


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

    Swal.fire(options).then((result) =>
    {
      if (result.value)
      {
        successCallback();
        if (!(successTitle == "" && successMessage == ""))
        {
          const successOptions = {
            title: successTitle,
            text: successMessage,
            icon: 'success'
          } as SweetAlertOptions;
          Swal.fire(successOptions);
        }
      }
      else if (result.dismiss === Swal.DismissReason.cancel)
      {
        failureCallback();
        if (!(failureTitle == "" && failureMessage == ""))
        {
          const failureOptions = {
            title: failureTitle,
            text: failureMessage,
            icon: 'error'
          } as SweetAlertOptions;
          Swal.fire(failureOptions);
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

    Swal.fire(options);
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

    Swal.fire(options).then((result) =>
    {

      successCallback();

    });
  }
  loadingMenu<T, R>(message: string, obs: Observable<any>, successCallback: Function, successText: string,
    failureCallback: Function, failureText: string, showAlertOnFinish = true, resultTimer: number = 2500)
  {
    let res = false;
    Swal.fire({
      text: message,
      showCloseButton: false,
      showCancelButton: false,
      didOpen: (login) =>
      {
        Swal.showLoading();
        obs.subscribe((result: T) =>
        {
          successCallback(result);
          res = true;
          Swal.hideLoading();
          Swal.close();
        }, (error: R) =>
        {
          res = false;
          failureCallback(error);
          Swal.hideLoading();
          Swal.close();
        });
      },
      allowOutsideClick: () => !Swal.isLoading(),
    }).then(() =>
    {
      if (showAlertOnFinish)
      {
        if (res)
        {
          this.alert(successText, resultTimer, false, 'center', 'success');
        }
        else
        {
          this.alert(failureText, resultTimer, false, 'center', 'error');
        }
      }
    }
    );
  }

}