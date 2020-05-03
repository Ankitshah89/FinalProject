import { User } from './../models/user';
import { Business } from './../models/business';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/businesses/';


  public index() { //Shouldn't Need any Authorization
    // const credentials = this.authService.getCredentials();
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Content-Type': 'application/json',
    //     'Authorization': `Basic ${credentials}`
    //   })
    // };
    // if (this.authService.checkLogin()) {
    //   return this.http.get<Business[]>(this.url, httpOptions)
    //     .pipe(
    //       catchError((err: any) => {
    //         console.log(err);
    //         return throwError('Todo list could not be found');
    //       })
    //     );
    // } else {
    //   this.router.navigateByUrl('/businesses');

    // }
    return this.http.get<Business[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('List of Businesses could not be found');
        })
      );
  }

  public create(business: Business) {

    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.post<any>(this.url, business, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create new Business' + err);
        })
      )
    } else {
      this.router.navigateByUrl(`/home`);

    }
  }

  public destroy(id: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.delete<any>(this.url + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Business Service could not delete business: ' + id + err);

        })
      );
    } else {
      this.router.navigateByUrl(`/home`);
    }

  }
  public updateTodo(data: Business) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.put<Business>(this.url + data.id, data, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Update Business with ID: ' + data.id + ' failed.' + err)

        })
      )
    } else {
      this.router.navigateByUrl(`/home`);

    }
  }

  public show(id: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.get<Business>(this.url + id, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Could not find Business with id: ' + id);
          })
        );
    } else {
      this.router.navigateByUrl(`/home`);

    }
  }

  //Search by Name
  public searchName(keyword: string) {
    return this.http.get<Business[]>(this.url + "search/name/" + keyword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not find Business with name: ' + keyword);
        })
      );
  }
  public searchDescription(keyword: string) {
    return this.http.get<Business[]>(this.url + "search/description/" + keyword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not find Business with description: ' + keyword);
        })
      );
  }
  public searchZip(keyword: string) {
    return this.http.get<Business[]>(this.url + "search/zip/" + keyword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not find Business with zip: ' + keyword);
        })
      );
  }

  public businessByManager(user: User) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.post<Business[]>(this.url + "manager", user, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Could not retrieve list of Businesses from this user');

          })
        )
    }
  }
  //Get BusinessesBy Category - URL + search/category/ + category -- No Auth
  public businessesByCategory(category: String){
    return this.http.get<Business[]>(this.url + 'search/category/' + category)
    .pipe(
      catchError((err: any) =>{
        console.log(err);
        return throwError('Business Service: Failed to retrieve list by category');
      })
    );
  }
}


