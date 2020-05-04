import { UserService } from './../../services/user.service';
import { User } from 'src/app/models/user';
import { Business } from 'src/app/models/business';
import { Component, OnInit } from '@angular/core';
import { BusinessService } from 'src/app/services/business.service';
import { userInfo } from 'os';
import { Router } from '@angular/router';

@Component({
  selector: 'app-business-landing',
  templateUrl: './business-landing.component.html',
  styleUrls: ['./business-landing.component.scss'],
})
export class BusinessLandingComponent implements OnInit {
  businessListForOwner: Business[] = [];
  user: User = new User();
  userId: Number;

  constructor(
    private businessSvc: BusinessService,
    private userSVc: UserService,
    private router : Router
  ) {}

  ngOnInit(): void {
    // populate the user

    // this.showBusinessInfo();
    this.getUserIdFromEmail();
    this.showBusinessInfo();
  }


  showIndividualBusiness(id){
    console.log('******************showing individual business');
    localStorage.setItem("businessId", "");
    localStorage.setItem("businessId", String(id));
    this.router.navigate(['business']);
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

  showBusinessInfo() {
    console.log('i am responsible for dynamic content');
    this.user.id = Number(localStorage.getItem("userId"));
    this.businessListForOwner = [];
    this.businessSvc.businessByManager(this.user).subscribe((good) => {
      good.forEach((business) => {
        this.businessListForOwner.push(business);
        console.log('business list of manager');
        console.log(business);
      });
      (bad) => {
        console.log('didntWork');
      };
    });
  }

  getUserIdFromEmail() {
    // localStorage.setItem('userId', "");
    console.log('getUserByemail is called');

    this.userSVc.searchByEmail(localStorage.getItem("email")).subscribe(
      (next) => {
        console.log('inside next');
        localStorage.setItem("userId", String(next.id));
        console.log(next);
        this.userId = next.id;
      },
      (error) => {
        console.log('inside error');
        this.userId = error.id;
        console.log(error);
      }
    );
  }


}
