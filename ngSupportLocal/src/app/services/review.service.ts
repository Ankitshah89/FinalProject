import { throwError } from 'rxjs';
import { Review } from './../models/review';
import { PreferenceService } from './preference.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
  private url = environment.baseUrl + 'api/'; //This will require checking in Spring to map. The mappings do not coincide with a single path

  //Get index of reviews - URL + "users/reviews" -- No Auth
  public indexReview() {
    return this.http.get<Review[]>(this.url + 'users/reviews').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve a list of All Reviews');
      })
    );
  }
  //Get index of reviews by user id - URL + "users/uid/reviews" -- No Auth
  public indexReviewById(uid: number) {
    return this.http.get<Review[]>(this.url + 'users/' + uid + '/reviews').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve a list of All Reviews by id');
      })
    );
  }

  //Get Business Reviews by ID - URL + businesses/ + bid + /reviews -- No Auth
  public businessReviews(bid: number) {
    return this.http.get<Review[]>(this.url + 'businesses/' + bid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Could not retrieve a list of reviews of business by id: ' + bid
        );
      })
    );
  }

  //Post Review for business ID - URL + busisnesses / + bid + /reviews -- Needs Auth
  public postBusReview(review: Review, bid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http
        .post<Review>(this.url + 'businesses/' + bid, review, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              'Could not post New Review to business id: ' + bid
            );
          })
        );
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }
  //Put  Update Business Review by BID and RID - URL + businesses/ + bid + /reviews/ + rid -- Needs Auth
  public updateBusReview(review: Review, bid: number, rid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http
        .put<Review>(
          this.url + 'businesses/' + bid + '/reviews/' + rid,
          review,
          httpOptions
        )
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              'Could not update Review for Business: ' + bid + ' Review: ' + rid
            );
          })
        );
    }
  }
  //Delete Review by Review ID - URL + reviews/ + rid  -- Needs Auth
  public deleteReview(rid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http
        .delete<any>(this.url + 'reviews/' + rid, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('Could not delete review with ID: ' + rid);
          })
        );
    }
  }
}
