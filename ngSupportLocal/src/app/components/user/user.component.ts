import { UserService } from 'src/app/services/user.service';
import { ArticleService } from './../../services/article.service';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/article';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
user: new User();
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  user: User = new User();
  constructor(
    private currentRoute: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    const userIdStr = this.currentRoute.snapshot.paramMap.get('id');
    if (userIdStr) {
      const userId = Number.parseInt(userIdStr, 10);
      this.userService.show(userId).subscribe(
        (yay) => {
          console.log('articles' + yay.articles);

          console.log(yay);
          this.user = yay;
        },
        (nay) => {
          console.log('error in user component get profile');
        }
      );
    }
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
