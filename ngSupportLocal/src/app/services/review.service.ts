import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(
    private http: HttpClient,
    private authSer: AuthService,
    private routher: Router
  ) {

  }
  private url = environment.baseUrl + 'api/'; //This will require checking in Spring to map. The mappings do not coincide with a single path

}
