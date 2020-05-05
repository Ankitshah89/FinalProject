import { ReviewComment } from './../models/review-comment';
import { Article } from './../models/article';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ArticleComment } from '../models/article-comment';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  selected: Article;

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
  private url = environment.baseUrl + 'api/articles/';

  //GetIndex - URL -- No Auth
  public index() {
    return this.http.get<Article[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve full index of Articles');
      })
    );
  }
  //GetIndex - URL -- No Auth
  public showById(aid: number) {
    return this.http.get<Article>(this.url + aid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve article by id');
      })
    );
  }

  //Get Index By Business URL + business/ + bid -- No Auth
  public indexBusArt(bid: number) {
    return this.http.get<Article[]>(this.url + 'business/' + bid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Could not retrieve list of articles by business id: ' + bid
        );
      })
    );
  }

  //Get Index by User - URL + user/ -- No Auth
  public indexUserArt() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Headers': 'Content-Type',
        // 'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http.get<Article[]>(this.url + 'user', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve list of articles by user id: ');
      })
    );
  }

  //Get Article by ID - URL + aid  -- No Auth
  public artByUser(uid: number) {
    return this.http.get<Article>(this.url + uid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve article by id: ' + uid);
      })
    );
  }
  public articlesByUserId(uid: number) {
    return this.http.get<Article[]>(this.url + 'user/' + uid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve article by id: ' + uid);
      })
    );
  }

  //Post Create New Article - URL -- Needs Auth
  public postArticle(article: Article) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.post<Article>(this.url, article, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not post new article');
        })
      );
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }

  //Post Artilce by Business ID - URL + business/ + bid -- Needs Auth
  public postArticleByBusiness(article: Article, bid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http
        .post<Article>(this.url + 'business/' + bid, article, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              'Could not post new article to Business id: ' + bid
            );
          })
        );
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }

  //Put Update Article by ID - URL + aid -- Needs Auth
  public updateArticleById(article: Article, aid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.put<Article>(this.url + aid, article, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Could not post new article to Business id: ' + aid
          );
        })
      );
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }

  //Delete Article by ID - URL + aid -- Needs Auth
  public deleteArticleById(aid: number) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ${credentials}`,
      }),
    };
    if (this.authService.checkLogin()) {
      return this.http.delete<Article>(this.url + aid, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not delete article by id: ' + aid);
        })
      );
    } else {
      this.router.navigateByUrl(`/home`);
    }
  }

  loadArticleComments() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http
      .get<ArticleComment[]>(
        this.url + 'api/articles/' + this.selected.id + '/comments',
        httpOptions
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('ArticleService.loadReviewComments(): Error');
        })
      );
  }
}
