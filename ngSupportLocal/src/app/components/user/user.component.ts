import { Review } from './../../models/review';
import { ReviewService } from 'src/app/services/review.service';
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
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  user: User;
  phone: string;
  articles: Article[] = [];
  articleComments: ArticleComment[] = [];
  individualComments: ArticleComment[] = [];
  reviews: Review[] = [];
  newComment: ArticleComment;
  constructor(
    private currentRoute: ActivatedRoute,
    private userService: UserService,
    private articleService: ArticleService,
    private articleCommentService: ArticleCommentService,
    private reviewService: ReviewService
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
          this.phone = this.formatPhoneNumber(yay.phone);
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
          this.getUserReviews();
        },
        (nay) => {
          console.log('error in getArticleComments');
        }
      );
    });
  }

  getUserReviews() {
    this.reviewService.indexReviewById(this.user.id).subscribe(
      (yay) => {
        this.reviews = yay;
        console.log('reviews' + yay.length);
      },
      (nay) => {
        console.log('error retrieving user profile reviews');
      }
    );
  }

  postArticleComment(articleCommentForm: NgForm, articleId: number) {
    //  this.articleList.forEach((article) => {

    console.log(articleId);
    console.log(articleCommentForm);
    console.log(articleCommentForm.value);
    var commentData: ArticleComment = articleCommentForm.value;

    console.log(commentData);
    delete commentData.articleId;

    console.log('this is the comment content' + commentData.content);
    console.log('this is the comment articleid' + articleId);

    commentData.user = this.user;
    this.articleCommentService.postComment(commentData, articleId).subscribe(
      (data) => {
        this.newComment = data;
        console.log('new comment success' + this.newComment);
        this.reloadAgain();
      },
      (error) => {
        console.log('error posting new comment' + this.newComment);
      }
    );
  }

  reloadAgain() {
    this.getArticleComments();
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
  showComments(aid: string) {
    var commentBox = document.getElementById('commentDiv' + aid);
    var btnText = document.getElementById('divBtn' + aid);

    if (commentBox.style.display === 'none') {
      commentBox.style.display = 'block';
      btnText.innerHTML = 'Hide Comments';
    } else {
      commentBox.style.display = 'none';
      btnText.innerHTML = 'Show Comments';
    }
  }

  formatPhoneNumber = (str) => {
    //Filter only numbers from the input
    let cleaned = ('' + str).replace(/\D/g, '');

    //Check if the input is of correct length
    let match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);

    if (match) {
      return '(' + match[1] + ') ' + match[2] + '-' + match[3];
    }

    return null;
  };
}
