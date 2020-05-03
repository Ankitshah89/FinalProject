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
}
