import { ArticleComment } from './../../models/article-comment';
import { User } from './../../models/user';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { ReviewService } from './../../services/review.service';
import { UserService } from 'src/app/services/user.service';
import { ArticleService } from './../../services/article.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from 'src/app/models/article';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent implements OnInit {
  articleList: Article[] = [];
  selected : Article;
  keyword: string = null;
  list = false;
  rating = 0;
  averageRating = 0;


  articleComments: ArticleComment[];

  constructor(
    private articleSvc: ArticleService,
    private router: Router,
    private userSvc: UserService,
    private review: ReviewService,
    private business: BusinessService,
    private authService: AuthService
  ) {}

  categories = ['All', 'Sports', 'Entertainment', 'Shopping'];

  selectedType = 'All';

  ngOnInit(): void {
    this.selected= null;
    this.loadArticles();


  }

  loadArticles() {
    this.articleList = [];
    this.articleSvc.index().subscribe(
      (good) => {
      good.forEach((article) => {
        if (article.active) {
          this.articleList.push(article);
        }
      });
      (bad) =>{
        console.log('didntWork')
      }
    });
  }

   displayArticleComments(article : Article){
     this.selected = article;
     this.articleSvc.selected = article;
     this.articleSvc.loadArticleComments().subscribe(
       good => {
        this.articleComments = good;
       },
       bad =>{

       }
     )



  }

}
