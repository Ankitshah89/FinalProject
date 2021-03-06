import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class YelpApiService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/yelp/';


  public searchByPhone(phoneNumber : string){
    let httpOptions = {};
    if(this.authService.checkLogin()){
    const credentials = this.authService.getCredentials();
     httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
  }
      return this.http.get<any>(this.url + phoneNumber, httpOptions)
      .pipe(
        catchError((err: any) =>{
          console.log(err);
          return throwError('Could not retrieve list of Yelp reviews');

        })
      )
  }



}
