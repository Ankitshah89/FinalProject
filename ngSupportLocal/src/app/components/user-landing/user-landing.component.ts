import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { BusinessService } from 'src/app/services/business.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ArticleService } from 'src/app/services/article.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.scss'],
})
export class UserLandingComponent implements OnInit {
  // commentList : Article[] =[];

  articleList: Article[] = [];
  newArticle: Article = new Article();
  articles: Article[] = [];
  loggedInUser: User = new User();
  editUser = null;
  currentUser = null;
  newUserArticle = null;
  userName = '';


  categories = ['Sports', 'Food', 'Entertainment', 'Shopping'];

  constructor(
    private userService: UserService,
    private businessSvc: BusinessService,
    private router: Router,
    private authService: AuthService,
    private articleSvc: ArticleService
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
    this.authService.getUserByEmail(this.authService.getLoggedInEmail()).subscribe(
      foundUser => {
        this.loggedInUser = foundUser;
        console.log("The following was found for user:");

        console.log(foundUser);

      }, failure => {
        console.log(failure);


      }
    )
  }

  loadUserArticles() {
    this.articleList = [];
    this.articleSvc.indexUserArt().subscribe(
      (yes) => {
        this.articleList = yes;
        console.log(this.articleList);
      },
      (no) => { }
    );
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
    updateUser.active = user.active

    this.userService.updateUser(updateUser).subscribe(
      yes => {
        this.reload();
        console.log(yes);

        //this.currentUser = yes;
        this.editUser = null;
      },
      no => {
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
      go => {

        console.log('good to go')
        this.newUserArticle = go;
      },
      nogo => {
        console.error('PostArticleComponent: error');
        console.error(nogo);


      }
    )

  }

  reload(){
    this.userService.showLoggedInUser().subscribe(
      data => {
        this.currentUser = data;
        console.log("NEW USER")
        console.log("LOGGED IN USER --->" +this.currentUser)
        console.log(data);
      },
      error => {
        console.log("error inside show logged in user");
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
