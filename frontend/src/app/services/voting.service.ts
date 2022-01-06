import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SuperService } from './super.service';

@Injectable({
  providedIn: 'root'
})
export class VotingService 
{

  constructor(private superService: SuperService) { }

  getAllCandidates(): Observable<any>
  {
    return this.superService.get("/elector/candidate");
  }

  vote(electorId: number, candidateId: number)
  {
    return this.superService.postWithParams("/vote", {elector: electorId, candidate: candidateId}, null);
  }
}
