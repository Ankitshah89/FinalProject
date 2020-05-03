import { Component, OnInit } from '@angular/core';
import { Business } from 'src/app/models/business';
import { Review } from 'src/app/models/review';
import { BusinessService } from 'src/app/services/business.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { ReviewService } from 'src/app/services/review.service';
import { YelpApiService } from 'src/app/services/yelp-api.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-businesses',
  templateUrl: './businesses.component.html',
  styleUrls: ['./businesses.component.css']
})
export class BusinessesComponent implements OnInit {

  businessList: Business[] = [];
  selected: Business;
  keyword: string = null;
  list = false;
  rating = 0;
  averageRating = 0;

  businessReviews: Review[];

  constructor(
    private businessSvc: BusinessService,
    private router: Router,
    private userSvc: UserService,
    private review: ReviewService,
    private yelpApi: YelpApiService,
    private authService: AuthService
  ) {}


  categories = ['All', 'Sports', 'Entertainment', 'Shopping'];

  selectedType = 'All';

  ngOnInit(): void {
    this.selected = null;
    this.loadBusiness();
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
