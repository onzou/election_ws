import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SuperService } from './super.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{
  constructor(private http: HttpClient,
              private superService: SuperService) 
  {
  }

  public isLoggedIn()
  {
    return sessionStorage.getItem('user') != null;
  }

  public login(userCredentials: any)
  {
    return this.superService.post("/elector/login",userCredentials);
  }
}
