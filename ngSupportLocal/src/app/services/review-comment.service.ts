import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ReviewComment } from '../models/review-comment';

@Injectable({
  providedIn: 'root'
})
export class ReviewCommentService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/comments';

  //Get Index of All Reviews - URL -- No Auth
  public reviewIndex(){
    return this.http.get<ReviewComment[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not review index of all Review Comments')

      })
    );
  }

  //Get Review by ID - URL + rid -- No Auth
  public reviewById(rid: number){
    return this.http.get<ReviewComment>(this.url + '/' + rid)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retriev Review Comment id: ' + rid);
      })
    );
  }

  //Post New Review Comment - URL + / + rid -- Needs Auth
  public postReviewComment(rCom: ReviewComment, rid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.post<ReviewComment>(this.url + '/' + rid, rCom, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create a new comment on review id: ' + rid);

        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }
  //Put Update Comment - URL + / + rid -- Needs Auth
  public updateComment(rCom: ReviewComment, rid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.put<ReviewComment>(this.url + '/' + rid, rCom, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not update comment with id: ' + rid);
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }

  //Delete Comment by Id - URL + / + id -- Needs Auth
  public deleteCommentById(rid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.delete<any>(this.url + '/' + rid, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not delete comment with id:' + rid)
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }


}
