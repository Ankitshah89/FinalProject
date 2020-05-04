import { AddressService } from 'src/app/services/address.service';
import { YelpReview } from './../../models/yelp-review';
import { YelpApiService } from './../../services/yelp-api.service';
import { Component, OnInit } from '@angular/core';
import { Business } from 'src/app/models/business';
import { Review } from 'src/app/models/review';
import { BusinessService } from 'src/app/services/business.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { ReviewService } from 'src/app/services/review.service';
import { AuthService } from 'src/app/services/auth.service';

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

  constructor(
    private businessSvc: BusinessService,
    private router: Router,
    private userSvc: UserService,
    private review: ReviewService,
    private addressSvc: AddressService,
    private yelpApiSvc: YelpApiService,
    private authService: AuthService
  ) {}

  categories = ['All', 'Sports', 'Entertainment', 'Shopping'];

  selectedType = 'All';

  ngOnInit(): void {


    this.selected = null;
    this.getIndividualBusiness();
    this.logReviews();
    this.loadBusiness();
  }
  getIndividualBusiness() {

    console.log(
      'i will populate the business info for ' +
        localStorage.getItem('businessId')
    );

    this.businessSvc.businessById(localStorage.getItem('businessId')).subscribe(
      (result) => {

        this.individualBusiness = Object.assign({}, result);
        localStorage.setItem('phone', result.phone);
        this.displayYelpReviews();

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

