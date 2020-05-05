import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
  private url = environment.baseUrl + 'api/users/';

  //Index of Users

  public index() {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.get<User[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could retrieve list of Users');
        })
      );
    }
  }
  public show(uid: number) {
    return this.http.get<User>(this.url + 'profile/' + uid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve user');
      })
    );
  }



  public showLoggedInUser(){
    const credentials = this.authService.getCredentials();
    const id = this.authService.getLoggedInUserId();
    console.log("IDDDDDDDD----->" + id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    console.log("ShowLoggedInUser "+id);
      return this.http.get<User>(this.url +"profile/" +id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show logged in user in user service failed');
        })
      );
    }

  //Update Users
  public updateUser(user: User) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.put<User>(this.url, user, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not update user ');
        })
      );
    }
  }

  updateUserAsAdmin(user: User, id:number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    return this.http.put(this.url + id, user, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        // console.log('update method User Service');
        return throwError('user.service.ts Error: Update Method');
      })
    );
  }

  //Create Users

  public createUser(user: User) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.post<User>(this.url, user, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create new user');
        })
      );
    }
  }
  //Get - Business Favorites

  //Post - User Favorites

  // search by eamil
  public searchByEmail(email: string) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };

    return this.http.get<User>(this.url + email, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not find User with name: ' + email);
      })
    );
  }

  public disableUser(id: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.delete<User>(this.url + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('delete method in user service failed');
        })
      );
    }
  }
}
