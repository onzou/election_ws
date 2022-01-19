import { Injectable } from '@angular/core';
import { SuperService } from './super.service';

@Injectable({
  providedIn: 'root'
})
export class ResultsService 
{

  constructor(private api: SuperService) { }

  getResultsInRegions()
  {
    return this.api.get("/results/region");
  }
}
