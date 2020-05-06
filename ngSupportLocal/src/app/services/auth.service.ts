import { environment } from './../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  login(email, password) {
    localStorage.setItem('email', email);

    const credentials = this.generateBasicAuthCredentials(email, password);
    console.log('In login function: ' + credentials);

    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // create request to authenticate credentials
    return this.http.get<User>(this.baseUrl + 'authenticate', httpOptions).pipe(
      tap((res) => {
        console.log(' ***********************************');
        console.log(res);
        localStorage.setItem('credentials', credentials);
        localStorage.setItem('userId', String(res.id));
        console.log('USER ID --> ' + localStorage.getItem('userId'));
        return res;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.login(): Error logging in.');
      })
    );
  }

  register(user) {
    console.log(user);

    // create request to register a new account
    return this.http.post(this.baseUrl + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('email');
    localStorage.removeItem('credentials');
    localStorage.removeItem('currentUserId');
    localStorage.removeItem('userId');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(email, password) {
    return btoa(`${email}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  getCurrentUserId() {
    return localStorage.getItem('currentUserId');
  }

  getLoggedInUserId() {
    return localStorage.getItem('userId');
  }

  getCurrentBusinessId() {
    return localStorage.getItem('businessId');
  }

  getLoggedInEmail() {
    console.log('This is the email: ' + localStorage.getItem('email'));

    return localStorage.getItem('email');
  }
  getUserByEmail(email: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Headers': 'Content-Type',
        // 'Content-Type': 'application/json',
        Authorization: `Basic ` + this.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http
      .get<User>(this.baseUrl + 'api/users/' + email, httpOptions)
      .pipe(
        catchError((err: any) => {
          // console.log(err);
          return throwError(
            'AuthService.getUserByEmail(): error finding user.'
          );
        })
      );
  }
}
