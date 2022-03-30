import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit 
{

  isModalOpened: boolean = false;
  isLoginOpened: boolean = true;
  user: any = null;
  hasUserVoted: boolean = false; 

  constructor(private auth: AuthService) { }
 
  ngOnInit(): void 
  {
    const header = document.getElementById("header");
    if(header != null)
    {
      window.addEventListener("scroll",(event)=>
      {
        header.classList.toggle("scrolled",window.scrollY> 50)
      });
    }
   
    document.onkeydown = evt => 
    {
      evt = evt || window.event;
      evt.keyCode === 27 ? this.closeModal() : false;
    };
    if(this.isUserLoggedIn())
    {
      this.user = sessionStorage.getItem('user');
      this.hasUserVoted = this.checkUserVote();
    }
  }

  isUserLoggedIn()
  {
    return this.auth.isLoggedIn();
  }

  private checkUserVote()
  {
    let myUser: any;
    this.auth.getUserById(this.user)
        .subscribe((data:any) =>
        {
          this.auth.reloadUser(Number(this.user));
          
          myUser = data;

        });
    return myUser.hasVoted;
        
  }

  openModal()
  {
    this.isModalOpened = true;
  }

  closeModal()
  {
    this.isModalOpened = false;
  }

  toggleSignInSignUp()
  {
    this.isLoginOpened = !this.isLoginOpened;
  }

  login(userCredentials: any)
  {
    this.auth.login(userCredentials)
              .subscribe((data: HttpResponse<any>) => 
              {
                let token = data.headers.get('authorization');
                let userId = data.headers.get('userId');
                sessionStorage.setItem('token',String(token));
                sessionStorage.setItem('user',String(userId));
                this.closeModal();
                window.location.href = "/";
                
              },(error: HttpErrorResponse)=>
              {

              },() => {}
              );
  }

  signup(userCredentials: any)
  {
    console.log(userCredentials);
    
  }
}
