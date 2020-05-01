import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArticleCommentService {

  constructor(
    private http: HttpClient,
    private authSer: AuthService,
    private routher: Router
  ) {

  }
  private url = environment.baseUrl + 'api/article/comments/';

}
