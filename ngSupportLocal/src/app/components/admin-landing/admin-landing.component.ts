import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-landing',
  templateUrl: './admin-landing.component.html',
  styleUrls: ['./admin-landing.component.css']
})
export class AdminLandingComponent implements OnInit {

  users: User[] = [];
  selectedUser: User = null;
  user: User = null;
  updateUser: User = null;
  disableUser: User = null;

  // Gear
  gearList: Gear[] = [];
  gear: Gear = null;
  selectedGear: Gear = null;

  // Reservations
  resvList: Reservation[] = [];
  resv: Reservation = null;
  selectedResv: Reservation = null;

  // Reviews of Gear
  gearReviewList: ReviewOfGear[] = [];
  gearReview: ReviewOfGear = null;
  selectedGearReview: ReviewOfGear = null;

  admin: User = null;

  constructor(
    private userSvc: UserService,
    private gearSvc: GearService,
    private resvSvc: ReservationService,
    private authSvc: AuthService,
    private revSvc: ReviewOfLenderService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authSvc
      .getUserByUsername(this.authSvc.getLoggedInUsername())
      .subscribe(
        good => {
          this.user = good;
          console.log(this.user);
          if (this.user.role !== "admin") {
            this.router.navigateByUrl("/login");
          }
        },
        error => {}
      );

    this.loadUsers();
    this.loadGear();
    this.loadReservations();
    // this.loadReviews();
  }

  // Admin Check here not good
  adminLoggedInCheck() {}

  // Users **************************

  public loadUsers() {
    const userList: [] = [];

    this.userSvc.index().subscribe(
      good => {
        console.log(good);
        this.users = good;
      },
      bad => {
        console.log(bad);
      }
    );
  }

  public countUsers() {
    return this.users.length;
  }

  public countActiveU() {
    // set data aggr. for active users
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.users.length; i++) {
      for (this.user of this.users) {
        if (this.user.enabled) {
          count++;
        }
      }
      return count;
    }
  }

  public updatedUserEnabled(user: User) {
    if (user.role !== "admin") {
      if (user.enabled) {
        user.enabled = false;
      } else {
        user.enabled = true;
      }
      this.userSvc.updateUserAsAdmin(user).subscribe(
        uData => {
          console.log(user);

          // this.loadUsers();
          // this.selectedUser = null;
          // this.updatedUserEnabled = null;
        },
        uErr => {
          this.loadUsers();
          console.error("updatedUser: Error");
          console.error(uErr);
          console.log(user);
        }
      );
    }
  }

  // Gear **************************

  public loadGear() {
    // this.clearSearch();
    this.gearSvc.index().subscribe(
      gData => {
        console.log(gData);
        this.gearList = gData;
      },
      didntWork => {
        console.log(didntWork);
      }
    );
  }

  public displayGearItem(gear: Gear) {
    this.selectedGear = gear;
  }

  public countGear() {
    return this.gearList.length;
    // Add data aggr. for active count.
  }

  public countActiveG() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.gearList.length; i++) {
      for (this.gear of this.gearList) {
        if (this.gear.active) {
          count++;
        }
      }
      return count;
    }
  }

  public countAvailableG() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.gearList.length; i++) {
      for (this.gear of this.gearList) {
        if (this.gear.available) {
          count++;
        }
      }
      return count;
    }
  }

  // RESERVATIONS **************************

  public loadReservations() {
    this.resvSvc.index().subscribe(
      rData => {
        console.log(rData);
        this.resvList = rData;
      },
      rErr => {
        console.log(rErr);
      }
    );
  }

  public countResv() {
    return this.resvList.length;
  }

  public countActiveR() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    // for (let i = 0; i < this.resvList.length; i++) {
    for (let resv of this.resvList) {
      if (resv.approved && !resv.completed) {
        count++;
      }
      // }
    }
    return count;
  }

  public countCompletedR() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    // for (let i = 0; i < this.resvList.length; i++) {
    for (let resv of this.resvList) {
      if (resv.completed) {
        count++;
      }
      // }
    }
    return count;
  }

  public countNeedsApproval() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    // for (let i = 0; i < this.resvList.length; i++) {
    for (let resv of this.resvList) {
      if (!resv.approved) {
        count++;
      }
      // }
    }
    return count;
  }

  // Gear Reviews **************************

  public loadReviews() {
    // this.revSvc.index().subscribe(
    //   good => {
    //     console.log(good);
    //     this.gearReviewList = good;
    //   },
    //   bad => {
    //     console.log(bad);
    //     console.log('loadReviews Error');
    //   }
    // );
  }

}
