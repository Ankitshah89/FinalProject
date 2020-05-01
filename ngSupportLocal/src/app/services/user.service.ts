import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private routher: Router
  ) {

  }
  private url = environment.baseUrl + 'api/users/';

  //Index of Users

  public index(){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.get<User[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) =>{
          console.log(err);
          return throwError('Could retrieve list of Users');

        })
      )
    }
  }

  //Update Users
  public updateUser(user: User){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()){
      return this.http.put<User>(this.url + user.id, user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not update user with id: ' + user.id)

        })
      )
    };
  }
  //Create Users

  public createUser(user: User){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()){
      return this.http.post<User>(this.url, user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create new user');

        })
      )
    };
  }

}
