import { AddressService } from 'src/app/services/address.service';
import { YelpReview } from './../../models/yelp-review';
import { YelpApiService } from './../../services/yelp-api.service';
import { Component, OnInit } from '@angular/core';
import { Business } from 'src/app/models/business';
import { Review } from 'src/app/models/review';
import { BusinessService } from 'src/app/services/business.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { ReviewService } from 'src/app/services/review.service';
import { AuthService } from 'src/app/services/auth.service';
import { ArticleComment } from 'src/app/models/article-comment';
import { Article } from 'src/app/models/article';
import { ArticleCommentService } from 'src/app/services/article-comment.service';
import { ArticleService } from 'src/app/services/article.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-business',
  templateUrl: './business.component.html',
  styleUrls: ['./business.component.scss'],
})
export class BusinessComponent implements OnInit {
  businessList: Business[] = [];
  selected: Business;
  keyword: string = null;
  list = false;
  rating = '0';
  averageRating = 0;
  individualBusiness: Business = null;

  yelpBusiness: Business = new Business();

  businessReviews: Review[];

  yelpReviews: YelpReview[] = [];

  newComment: ArticleComment;
  reviews: Review[] = [];
  articleList: Article[] = [];
  user: User;
  phone: string;
  constructor(
    private businessSvc: BusinessService,
    private router: Router,
    private userSvc: UserService,
    private review: ReviewService,
    private addressSvc: AddressService,
    private yelpApiSvc: YelpApiService,
    private authService: AuthService,
    private actRouter: ActivatedRoute,
    private articleCommentService: ArticleCommentService,
    private articleSvc: ArticleService
  ) {}

  categories = ['All', 'Sports', 'Entertainment', 'Shopping'];

  selectedType = 'All';

  ngOnInit(): void {
    this.actRouter.params.subscribe((routeParams) => {
      this.getIndividualBusiness();
    });

    this.selected = null;
    this.getIndividualBusiness();
    this.logReviews();
    this.loadBusiness();
    this.displayYelpReviews();
  }

  getIndividualBusiness() {
    console.log(
      'i will populate the business info for ' +
        localStorage.getItem('businessId')
    );

    this.businessSvc
      .businessById(this.authService.getCurrentBusinessId())
      .subscribe(
        (result) => {
          this.individualBusiness = Object.assign({}, result);
          localStorage.setItem('phone', result.phone);
          this.displayYelpReviews();
          this.phone = this.formatPhoneNumber(result.phone);
        },
        (error) => {
          console.log(error);
        }
      );
  }

  logReviews() {
    console.log('inside log refviews');
    // console.log(this.yelpReviews);
    console.log(this.individualBusiness);
  }

  loadBusiness() {
    this.businessList = [];
    this.businessSvc.index().subscribe((good) => {
      good.forEach((business) => {
        if (business.active) {
          this.businessList.push(business);
        }
      });
      (bad) => {
        console.log('didntWork');
      };
    });
  }

  displayYelpReviews() {
    // localStorage.setItem('phoneNo', '3033829710');
    console.log('YELP REVIEWS');
    this.yelpReviews = [];
    console.log(this.individualBusiness.phone);
    this.yelpApiSvc
      .searchByPhone(localStorage.getItem('phone'))
      .subscribe((response) => {
        this.rating = response.rating;
        console.log('phone response was successful');
        console.log(response);
        this.yelpReviews.push(response.reviews);
        response.reviews.forEach((reviews) => {
          console.log('inside review');
          // this.yelpReviews.push(reviews);
        });
        (bad) => {
          console.log('didntWork');
        };
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

  loadBusinessArticles() {
    this.articleList = [];
    this.articleSvc
      .indexBusArt(Number(this.authService.getCurrentBusinessId()))
      .subscribe(
        (yes) => {
          this.articleList = yes;
          this.getArticleComments();
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
    this.review.indexReviewById(this.user.id).subscribe(
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
