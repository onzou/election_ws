import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { VotingService } from 'src/app/services/voting.service';
import { environment as env } from 'src/environments/environment';

@Component({
  selector: 'app-voting',
  templateUrl: './voting.component.html',
  styleUrls: ['./voting.component.scss']
})
export class VotingComponent implements OnInit 
{
  defaultCandidateImagePath: string = "../../assets/default_candidate.jpg"
  //booleans 
  isModalOpened: boolean = false;
  hasAlreadyVoted: boolean = false;

  hostFile: string = `${env.api}/file?filename=`;
  candidates: any[] = [];

  user: any;
  chosenCandidate: any = 0;

  constructor(private votingService: VotingService,
              private auth: AuthService ) { }

  ngOnInit(): void 
  {    
    this.getAllCandidates();
    let fromStorage = sessionStorage.getItem('userObject');
    if(fromStorage)
      this.user = JSON.parse(fromStorage);

    this.hasAlreadyVoted = this.user.hasVoted;
  }
  
  getAllCandidates()
  {
    this.votingService.getAllCandidates()
        .subscribe(
        {
          next: (data: any)=>
          {
            this.candidates = data;
          },
          error: (error: HttpErrorResponse)=>
          {
            console.error(error.message);
          }
        });
  }

  openModal(candidat: any)
  {
    this.chosenCandidate = candidat;
    this.isModalOpened = true;
  }

  closeModal() { this.isModalOpened = false; }

  vote()
  {
    this.votingService.vote(Number(sessionStorage.getItem('user')),this.chosenCandidate.id)
        .subscribe(
        {
          next: (data: HttpResponse<any>)=>
          {
            console.log(data.body);
          },
          error: (error: HttpErrorResponse)=>
          {
            console.error(error.message);
          }
        });
  }
}
