import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

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
  hasUserSubscribed: boolean = true; 
  hasLoginFailed: boolean = false; 

  isChangeVoteAreaModalOpened: boolean = false; 
  isSubscriptionModalOpened: boolean = false;
  

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
      this.checkUserSubscription(Number(this.user));
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
    return myUser && myUser.hasVoted;
        
  }

  openModal()
  {
    this.isModalOpened = true;
  }

  closeModal()
  {
    this.isModalOpened = false;
  }

  openChangeVoteAreaModal() { this.isChangeVoteAreaModalOpened = true; }

  closeChangeVoteAreaModal() { this.isChangeVoteAreaModalOpened = false; }

  openSubscriptionModal() { this.isSubscriptionModalOpened = true; }

  closeSubscriptionModal() { this.isSubscriptionModalOpened = false; }

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

                this.checkUserSubscription(Number(userId));
                window.location.href = "/";

              },(error: HttpErrorResponse)=>
              {
                if(error.status === 400)
                  this.hasLoginFailed = true;
              },() => {}
              );
  }

  logout()
  {
    this.auth.logout()
        .subscribe((data:any)=>
        {          
          localStorage.clear();
          window.location.href = "/";

        },(error: HttpErrorResponse)=>
        {
          console.error(error);
        });
  }

  private checkUserSubscription(electorId: number)
  {
    this.auth.checkUserSubscription(electorId)
        .subscribe((response: any)=>
        {          
          this.hasUserSubscribed = response.body;
          if(this.hasUserSubscribed == false)
          Swal.fire(
            {
              icon: 'error',
              title: 'Oops...',
              text: "Vous n'êtes sur aucune liste électorale"
            });
        });
  }

  signup(userCredentials: any)
  {    
    this.auth.subscribe(userCredentials)
              .subscribe((data:any)=>
              {
                this.closeModal();
                Swal.fire(
                  {
                    title: "Succès",
                    icon: "success",
                    text: "Inscription réussie!"
                  });
                
              },(error: HttpErrorResponse)=>
              {
                this.showErrorMessage(error.message);
              });
  }

  private showErrorMessage(text: string)
  {
    Swal.fire(
      {
        title: "Echec",
        icon: "error",
        text: `${text}`
      });
  }
  changeVoteArea(formValue: any)
  {

  }
}
