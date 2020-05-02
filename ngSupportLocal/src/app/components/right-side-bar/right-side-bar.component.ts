import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Business } from 'src/app/models/business';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-right-side-bar',
  templateUrl: './right-side-bar.component.html',
  styleUrls: ['./right-side-bar.component.scss'],
})
export class RightSideBarComponent implements OnInit {

  favorites = [];
  user: User;
  constructor(
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    // if (this.authService.checkLogin) {
    //   console.log('Getting User Email for Favorites');

      this.authService.getUserByEmail(this.authService.getLoggedInEmail()).subscribe(
        data => {
          this.user = data;
          console.log(data);
          console.log('This is the information coming into the right-nav bar');

          console.log(this.user);

          this.favorites=(this.user.favoriteBusinesses);

          // this.favorites.push(data.favoriteBusinesses);
          console.log(this.favorites);

        }
      ), error => {
        console.log(error);

      }
    // }
  }


}
