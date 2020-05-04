import { NullTemplateVisitor } from '@angular/compiler';
import { ArticleComment } from './../../models/article-comment';
import { ArticleCommentService } from './../../services/article-comment.service';
import { ArticleService } from 'src/app/services/article.service';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  user: User;
  articles: Article[] = [];
  articleComments: ArticleComment[] = [];
  individualComments: ArticleComment[] = [];
  constructor(
    private currentRoute: ActivatedRoute,
    private userService: UserService,
    private articleService: ArticleService,
    private articleCommentService: ArticleCommentService
  ) {}

  ngOnInit(): void {
    this.getUserProfile();
  }

  getUserProfile() {
    const userIdStr = this.currentRoute.snapshot.paramMap.get('id');
    if (userIdStr) {
      const userId = Number.parseInt(userIdStr, 10);
      this.userService.show(userId).subscribe(
        (yay) => {
          this.user = yay;
          this.getUserArticles();
        },
        (nay) => {
          console.log('error in user component get profile');
        }
      );
    }
  }

  getUserArticles() {
    console.log('user articles' + this.user.articles);
    this.articleService.articlesByUserId(this.user.id).subscribe(
      (yay) => {
        this.articles = yay;
        console.log('articles' + yay);
        this.getArticleComments();
      },
      (nay) => {
        console.log('error in ts articlesbyuserid' + nay);
      }
    );
  }

  getArticleComments() {
    this.articles.forEach((article) => {
      const articleId = article.id;
      this.articleCommentService.show(articleId).subscribe(
        (yay) => {
          article.articleComments = yay;
          console.log(article.articleComments.length);
        },
        (nay) => {
          console.log('error in getArticleComments');
        }
      );
    });
  }

  myFunction() {
    var dots = document.getElementById('dots');
    var btnText = document.getElementById('myBtn');

    if (dots.style.display === '-webkit-box') {
      dots.style.display = 'inline';
      btnText.innerHTML = 'Read more';
    } else {
      dots.style.display = '-webkit-box';
      btnText.innerHTML = 'Read less';
    }
  }
  showComments() {
    var commentBox = document.getElementById('commentDiv');
    var btnText = document.getElementById('divBtn');

    if (commentBox.style.display === 'none') {
      commentBox.style.display = 'block';
      btnText.innerHTML = 'Hide Comments';
    } else {
      commentBox.style.display = 'none';
      btnText.innerHTML = 'Show Comments';
    }
  }
}
