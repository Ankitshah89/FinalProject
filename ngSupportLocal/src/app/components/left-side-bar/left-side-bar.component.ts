import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.scss'],
})
export class LeftSideBarComponent implements OnInit {
  user: User;
  constructor(
    private aSer: AuthService
  ) {}

  ngOnInit(): void {
    if(this.aSer.checkLogin()){
      this.aSer.getUserByEmail(this.aSer.getLoggedInEmail()).subscribe(
        data => {
          this.user = data;
          console.log('Left Hand Nav data');
          console.log(this.user);


        }
      )
    }
  }
}
