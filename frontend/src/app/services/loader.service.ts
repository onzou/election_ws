import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoaderService 
{
  private loading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false); 
  public isLoading = this.loading.asObservable();

  constructor() { }

  stopLoading(): void
  {
    this.loading.next(false);
  }

  startLoading():void 
  {
    this.loading.next(true);
  }
}
