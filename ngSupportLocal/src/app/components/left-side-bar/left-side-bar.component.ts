import { BusinessService } from 'src/app/services/business.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { subscribeOn } from 'rxjs/operators';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.scss'],
})
export class LeftSideBarComponent implements OnInit {
  user: User;
  constructor(
    private aSer: AuthService,
    private bSer: BusinessService
  ) { }

  ngOnInit(): void {
    if (this.aSer.checkLogin()) {
      this.aSer.getUserByEmail(this.aSer.getLoggedInEmail()).subscribe(
        data => {
          this.user = data;
          this.getBusinesses(data);
          console.log('Left Hand Nav data');
        }
      )
    }
  }

  public getBusinesses(user: User) {
    if (this.user != null) {
      console.log("Grabbing user Businessnes");

      this.bSer.businessByManager(user).subscribe(
        data => {
          this.user.businesses = data;
          console.log(this.user.businesses);

        }
      )
    }
  }
}
