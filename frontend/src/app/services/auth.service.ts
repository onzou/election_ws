import { Injectable } from '@angular/core';
import { SuperService } from './super.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{
  constructor(private superService: SuperService) 
  {
  }

  public isLoggedIn()
  {
    return sessionStorage.getItem('user') != null;
  }

  public login(userCredentials: any)
  {
    console.log("in loggin service!");
    
    return this.superService.post("/elector/login",userCredentials);
  }
}
