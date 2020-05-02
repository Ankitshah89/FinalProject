import { UserService } from 'src/app/services/user.service';
import { ArticleService } from './../../services/article.service';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {


  constructor() {}

  ngOnInit(): void {}

}
