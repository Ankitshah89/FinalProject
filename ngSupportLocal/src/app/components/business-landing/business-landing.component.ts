import { Article } from 'src/app/models/article';
import { UserService } from './../../services/user.service';
import { User } from 'src/app/models/user';
import { Business } from 'src/app/models/business';
import { Component, OnInit } from '@angular/core';
import { BusinessService } from 'src/app/services/business.service';
import { userInfo } from 'os';
import { Router } from '@angular/router';
import { ArticleService } from 'src/app/services/article.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-business-landing',
  templateUrl: './business-landing.component.html',
  styleUrls: ['./business-landing.component.scss'],
})
export class BusinessLandingComponent implements OnInit {
  businessListForOwner: Business[] = [];
  user: User = new User();
  userId: Number;
  newBusinessArticle: Article;

  constructor(
    private businessSvc: BusinessService,
    private userSVc: UserService,
    private router: Router,
    private articleSvc: ArticleService

  ) { }

  ngOnInit(): void {
    // populate the user

    // this.showBusinessInfo();
    this.getUserIdFromEmail();

  }


  showIndividualBusiness(id) {
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

  showBusinessInfo(user: User) {
    console.log('Attempting to Retrieve list of Businesses inside Business-Landing-Component');
    // this.user.id = Number(localStorage.getItem("userId"));

    console.log(user);

    this.businessListForOwner = [];
    this.businessSvc.businessByManager(user).subscribe(
      good => {
        const randomArray = good;
        randomArray.forEach((business) => {
          console.log('Using this user to find businesses:');

          console.log(user);

          this.businessListForOwner.push(business);
          console.log('business list of manager');
          console.log(business);
        })
      }, bad => {
        console.log('didntWork');
      });
  }

  getUserIdFromEmail() {
    // localStorage.setItem('userId', "");
    console.log('getUserByemail is called');

    this.userSVc.searchByEmail(localStorage.getItem("email")).subscribe(
      (next) => {
        console.log('Success: Found User inside Business-Landing-Component');
        localStorage.setItem("userId", String(next.id));
        this.userId = next.id;
        this.showBusinessInfo(next);
      },
      (error) => {
        console.log('inside error');
        this.userId = error.id;
        console.log(error);
      }
    );
  }


  postBusinessArticle(articleForm: NgForm) {
    var articleData:Article = articleForm.value;
    articleData.business = this.businessListForOwner[0];
    console.log(articleData);
    articleData.active = true;

    this.articleSvc.postArticle(articleData).subscribe(
      go => {

        console.log('good to go')
        this.newBusinessArticle = go;
      },
      nogo => {
        console.error('PostArticleComponent: error');
        console.error(nogo);


      }
    )

  }


}
