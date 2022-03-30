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
          console.log(data);
          
          sessionStorage.setItem("userObject", JSON.stringify(user));
        });
  }
}
