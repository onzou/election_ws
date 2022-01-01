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
   
    document.onkeydown = evt => {
      evt = evt || window.event;
      evt.keyCode === 27 ? this.closeModal() : false;
    };
  }

  isUserLoggedIn()
  {
    return this.auth.isLoggedIn();
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
              .subscribe(
                {
                  next: (data: HttpResponse<any>) => 
                  {
                    
                  },
                  error: (error: HttpErrorResponse) => {},
                  complete: () =>{}
                }
              );
  }

  signup(userCredentials: any)
  {
    console.log(userCredentials);
    
  }
}
