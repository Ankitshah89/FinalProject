import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ArticleComment } from '../models/article-comment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleCommentService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/article/';

  //Get Article Comment Index - URL + comments/ -- No Auth
  public index() {
    return this.http.get<ArticleComment[]>(this.url + "comments")
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrive list of all article comments')

      })
    );
  }

  //Get ArticleComment by ID - URL + comments/ cid -- No Auth
  public getArtCommById(aCid: number){
    return this.http.get<ArticleComment>(this.url + 'comments')
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Could not retrieve Article Comment by id: ' + aCid);
      })
    )
  }

  //Post Commment on Article - Url + aid + "/comments" -- Needs Auth
  public postComment(artComm: ArticleComment, aid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()){
      return this.http.post<ArticleComment>(this.url + 'comments/' + aid, httpOptions)
      .pipe(
        catchError((err: any) =>{
          console.log(err);
          return throwError('Could not post new comment to Article: ' + aid);

        })
      )
    } else {
      this.router.navigateByUrl(`/home`)
    }
  }

  //Post Reply to comment on Article - URL + aid + /comments/ + cid -- Needs Auth
  public replyToCommentOnArticle(response: ArticleComment, aid: number, cid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()){
      return this.http.post<ArticleComment>(this.url + aid + '/comments/' + cid, response, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not post reponse on article ' + aid + 'comment: ' + cid);
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }
  //Put Update Comment by ID - URL + comments/ + cid -- Needs Auth
  public updateCommentById(update: ArticleComment, cid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.put<ArticleComment>(this.url + 'comments/' + cid, update, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not update Article Comment by id: ' + cid);
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }
  //Delete Article Comment by ID - URL + comments/ + cid -- Needs Auth
  public deleteArticleById(cid: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()){
      return this.http.delete<ArticleComment>(this.url + 'comments/'  + cid, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not delete Article Comment by id: ' + cid)
        })
      )
    } else{
      this.router.navigateByUrl(`/home`);
    }
  }

}
