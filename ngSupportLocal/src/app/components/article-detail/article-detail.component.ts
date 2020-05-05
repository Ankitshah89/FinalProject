import { ArticleCommentService } from './../../services/article-comment.service';
import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/services/article.service';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/models/article';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { ArticleComment } from 'src/app/models/article-comment';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss'],
})
export class ArticleDetailComponent implements OnInit {
  article: Article;
  user: User;
  newComment: ArticleComment;
  constructor(
    private currentRoute: ActivatedRoute,
    private articleSvc: ArticleService,
    private articleCommentSvc: ArticleCommentService,
    private userSvc: UserService
  ) {}

  ngOnInit(): void {
    this.getArticleById();
  }

  getArticleById() {
    const ArticleIdStr = this.currentRoute.snapshot.paramMap.get('id');
    console.log('Article Id String ' + ArticleIdStr);

    if (ArticleIdStr) {
      const artId = Number.parseInt(ArticleIdStr, 10);
      this.articleSvc.showById(artId).subscribe(
        (yay) => {
          this.article = yay;
          console.log('success retrieving article');
          console.log(this.article.articleComments);
          this.getLoggedInUserByEmail();
        },
        (nay) => {
          console.log('error retrieving article ' + nay);
        }
      );
    }
  }

  getLoggedInUserByEmail() {
    this.userSvc.searchByEmail(localStorage.getItem('email')).subscribe(
      (next) => {
        this.user = next;
        console.log('got logged in user ' + next.firstName);
      },
      (nay) => {
        console.log('error in getting user by email');
      }
    );
  }

  postArticleComment(articleCommentForm: NgForm) {
    var commentData: ArticleComment = articleCommentForm.value;
    console.log('this is the comment content' + commentData.content);
    console.log('this is the comment articleid' + this.article.id);

    commentData.user = this.user;
    this.articleCommentSvc.postComment(commentData, this.article.id).subscribe(
      (data) => {
        this.newComment = data;
        console.log('new comment success' + this.newComment);
        this.reload();
      },
      (error) => {
        console.log('error posting new comment' + this.newComment);
      }
    );
  }

  reload() {
    this.getArticleById();
  }
}
