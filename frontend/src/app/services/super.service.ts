import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment as env } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SuperService 
{
  httpOptions = 
  {
    headers: new HttpHeaders( 
    {
      "Access-Control-Allow-Origin": "http://localhost:4200",
      Accept : "application/json",
      "Content-Type" : "application/json"
    })
  };
  
  private apiURL = env.api;

  constructor(private httpClient: HttpClient) { }

  get(uri: string, params?: any): Observable<any>
  {
    return this.httpClient.get(this.apiURL + uri + this.paramStringBuilder(params));
  }
  

  getById(uri: string, id: number): Observable<any>
  {
    return this.httpClient.get<any>(`${this.apiURL + uri}/${id}`);
  }

  post(uri: string, objectToPost: any): Observable<any>
  {
    return this.httpClient.post(`${this.apiURL + uri}`,objectToPost, {observe: 'response'});
  }


  update(uri: string, objectToUpdate: any)
  {
    return this.httpClient.put(`${this.apiURL + uri}`, objectToUpdate, {observe: 'response'})
  }

  deleteById(uri: string, id: number)
  {
    return this.httpClient.delete(`${this.apiURL + uri}/${id}`)
  }
  
  deleteAll(uri: string, params?: any): Observable<any>
  {
    return this.httpClient.delete(this.apiURL + uri + this.paramStringBuilder(params));
  }


  
  private paramStringBuilder(params: any)
  {
    if(params == null)
      return "";    
    let keys = Object.keys(params);
    let paramBuilder = "";
    let index = 0;
    keys.forEach(key => 
    { 
      if(index === 0)
        paramBuilder += "?" + key + "=" + params[key];
      else 
        paramBuilder += "&" + key + "=" + params[key];
      index++;
    });
    return paramBuilder;
  }
}
