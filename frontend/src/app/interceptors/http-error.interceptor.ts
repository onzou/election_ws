import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import {  Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../services/auth.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor 
{

  constructor(private toast: ToastrService, 
              private authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> 
  {
      return next.handle(request).pipe(
        catchError((error) => 
        {
          return this.errorHandler(error)
        })
      );
  }

  private errorHandler(error: HttpErrorResponse): Observable<HttpEvent<any>> 
  {
    let errorMessage: string = "";
    if(error.error instanceof ErrorEvent)
      errorMessage = error.error.message;
    else
      errorMessage = error.message;
    this.toast.error(errorMessage, "Echec");
    return throwError(error);
  }
}
