import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Preference } from '../models/preference';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreferenceService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/preferences/';

  //Get Index of Preferences - URL -- No Auth
  public indexPreferences(){
    return this.http.get<Preference[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve index of Preferences');
      })
    )
  }

  //Get Preference by ID - URL + pid -- No Auth
  public preferenceById(pid: number){
    return this.http.get<Preference>(this.url + pid)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve preference by ID: ' + pid);

      })
    )
  }

  //Delete Preference by ID - URL + pid -- Auth Needed
  public deletePreference(pid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.delete<Preference>(this.url + pid, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not delete Preference with id: ' + pid);
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }

  //Post New Preference - URL -- Auth Needed
  public createPreference(preference: Preference){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.post<Preference>(this.url, preference, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create new Preference')
        })
      )
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }

  //Put Update Preference - URL -- Auth Needed
  public updatePreferenceByID(preference: Preference, pid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.put<Preference>(this.url + pid, preference, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not update Preference with id: ' +pid)

        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }

  //Get Preferences by Type - URL + 'search/type/' + type -- No Auth
  public preferencesByType(type: string){
    return this.http.get<Preference[]>(this.url + 'search/type/' + type)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve list Preferences with type: ' + type);
      })
    )
  }

  //Get Preferences by Category - URL + 'search/category/' + category -- No Auth
  public prefrenceByCategory(category: string){
    return this.http.get<Preference[]>(this.url + 'search/category/' + category)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve list of Preferences with category: ' + category);

      })
    )
  }

}
