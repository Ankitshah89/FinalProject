import { ReviewService } from 'src/app/services/review.service';
import { ArticleCommentService } from './../../services/article-comment.service';
import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { BusinessService } from 'src/app/services/business.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ArticleService } from 'src/app/services/article.service';
import { NgForm } from '@angular/forms';
import { Review } from 'src/app/models/review';
import { ArticleComment } from 'src/app/models/article-comment';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.scss'],
})
export class UserLandingComponent implements OnInit {
  // commentList : Article[] =[];

  newComment: ArticleComment;
  reviews: Review[] = [];
  articleList: Article[] = [];

  newArticle: Article = new Article();
  articles: Article[] = [];
  loggedInUser: User = new User();
  editUser = null;
  currentUser = null;
  newUserArticle = null;
  userName = '';
  user: User;

  categories = ['Sports', 'Food', 'Entertainment', 'Shopping'];

  constructor(
    private userService: UserService,
    private businessSvc: BusinessService,
    private router: Router,
    private authService: AuthService,
    private articleSvc: ArticleService,
    private articleCommentService : ArticleCommentService,
    private currentRoute: ActivatedRoute,
    private reviewService : ReviewService


  ) { }

  ngOnInit(): void {
    const cred = this.authService.getCredentials();
    if (cred === null) {
      this.router.navigateByUrl('/login');
    }
    this.loadUserArticles();
    this.setUsername();
    this.setUser();

  }

  setUser() {
    this.loggedInUser = null;
    this.authService
      .getUserByEmail(this.authService.getLoggedInEmail())
      .subscribe(
        (foundUser) => {
          this.loggedInUser = foundUser;
          console.log('The following was found for user:');

          console.log(foundUser);
        },
        (failure) => {
          console.log(failure);
        }
      );
  }

  loadUserArticles() {
    this.articleList = [];
    this.articleSvc.indexUserArt().subscribe(
      (yes) => {
        this.articleList = yes;
        this. getArticleComments();
        console.log(this.articleList);
      },
      (no) => {}
    );
  }


  getArticleComments() {
    this.articleList.forEach((article) => {
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



 postArticleComment(articleCommentForm: NgForm,articleId :number) {
  //  this.articleList.forEach((article) => {

console.log(articleId);
  console.log(articleCommentForm);
  console.log(articleCommentForm.value);
    var commentData: ArticleComment = articleCommentForm.value;

  console.log(commentData);
    delete  commentData.articleId;

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



  setUsername() {
    var user = localStorage.getItem('email').split('@', 1);
    this.userName = user[0];
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.loggedInUser);
  }

  updateUser(user: User) {
    var updateUser: User = new User();
    updateUser.id = user.id;
    if (user.password == null) {
      updateUser.password = this.loggedInUser.password;
    } else {
      updateUser.password = user.password;
    }
    updateUser.firstName = user.firstName;
    updateUser.lastName = user.lastName;
    updateUser.email = user.email;
    updateUser.phone = user.phone;
    updateUser.role = user.role;
    updateUser.createdAt = user.createdAt;
    updateUser.active = user.active;

    this.userService.updateUser(updateUser).subscribe(
      (yes) => {
        this.reload();
        console.log(yes);

        //this.currentUser = yes;
        this.editUser = null;
      },
      (no) => {
        console.error('UserProfileComponent.updateUser(): error');
        console.error(no);
      }
    );
  }

  postUserArticle(articleForm: NgForm) {
    const articleData = articleForm.value;
    console.log(articleData);
    articleData.active = true;

    this.articleSvc.postArticle(articleData).subscribe(
      (go) => {
        console.log('good to go');
        this.newUserArticle = go;
        location.reload();
      },
      (nogo) => {
        console.error('PostArticleComponent: error');
        console.error(nogo);
      }
    );
  }

  reload() {
    this.userService.showLoggedInUser().subscribe(
      (data) => {
        this.loggedInUser = data;
        console.log('NEW USER');
        console.log('LOGGED IN USER --->' + this.loggedInUser);
        console.log(data);
      },
      (error) => {
        console.log('error inside show logged in user');
      }
    );
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
