import { Injectable } from '@angular/core';
import { SuperService } from './super.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{
  
  constructor(private superService: SuperService) { }

  public isLoggedIn()
  {
    return sessionStorage.getItem('user') != null;
  }

  subscribe(userCredentials: any) 
  {
    return this.superService.post("/elector/subscribe",userCredentials)
  }

  logout()
  {  
    return this.superService.post("/elector/logout",null);
  }

  isUserLoggedIn()
  {
    return sessionStorage.getItem('token') != null;
  }

  private getUserFromLocal()
  {
    return JSON.parse(JSON.parse(JSON.stringify(sessionStorage.getItem('userObject')))); 
  }

  public login(userCredentials: any)
  {    
    return this.superService.post("/elector/login",userCredentials);
  }

  getUserById(userId: number)
  {
    return this.superService.getById("/elector", userId);
  }

  reloadUser(userId: number)
  {
    this.getUserById(userId)
        .subscribe((data:any) =>
        {
          let user: any = data;        
          sessionStorage.setItem("userObject", JSON.stringify(user));
        });
  }

  checkUserSubscription(electorId: number)
  {
    return this.superService.post("/elector/check-user-subscription", {"id": electorId});
  }
}
