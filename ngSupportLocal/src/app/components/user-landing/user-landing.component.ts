import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { BusinessService } from 'src/app/services/business.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.css']
})
export class UserLandingComponent implements OnInit {

  articleList: Article[] = [];
  newArticle: Article = new Article();
  articles: Article[] = [];
  loggedInUser: User = new User();

  categories = ['Sports', 'Food', 'Entertainment', 'Shopping'];

  constructor(
    private userService : UserService,
    private businessSvc: BusinessService,
    private router: Router,
    private authService: AuthService,
    private articleSvc: ArticleService
  ) {}

  ngOnInit(): void {
    const cred = this.authService.getCredentials();
    if (cred === null) {
      this.router.navigateByUrl('/login');
    }
    this.loadUserArticles();
  }

  loadUserArticles() {
    this.articleList = [];
    this.articleSvc.indexUserArt().subscribe(
      (yes) => {
        this.articleList = yes;
        console.log(this.articleList);
      },
      (no) => {}
    );
  }
}
