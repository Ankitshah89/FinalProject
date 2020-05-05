import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Business } from 'src/app/models/business';
import { UserService } from 'src/app/services/user.service';
import { BusinessService } from 'src/app/services/business.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-landing',
  templateUrl: './admin-landing.component.html',
  styleUrls: ['./admin-landing.component.css'],
})
export class AdminLandingComponent implements OnInit {
  users: User[] = [];
  selectedUser: User = null;
  user: User = null;
  updateUser: User = null;
  disableUser: User = null;

  businessList: Business[] = [];
  business: Business = null;
  selectedBusiness: Business = null;

  admin: User = null;

  constructor(
    private userSvc: UserService,
    private businessSvc: BusinessService,
    private authSvc: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authSvc.getUserByEmail(this.authSvc.getLoggedInEmail()).subscribe(
      (good) => {
        this.user = good;
        console.log(this.user);
        if (this.user.role !== 'Admin') {
          this.router.navigateByUrl('/login');
        }
      },
      (error) => {}
    );

    this.loadUsers();
    this.loadBusiness();
  }



  // Users **************************

  public loadUsers() {
    const userList: [] = [];

    this.userSvc.index().subscribe(
      (good) => {
        console.log(good);
        this.users = good;
      },
      (bad) => {
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
        if (this.user.active) {
          count++;
        }
      }
      return count;
    }
  }
  // Business **************************

  public loadBusiness() {
    // this.clearSearch();
    this.businessSvc.adminIndex().subscribe(
      (bData) => {
        console.log(bData);
        this.businessList = bData;
      },
      (didntWork) => {
        console.log(didntWork);
      }
    );
  }

  public displayBusinessItem(business: Business) {
    this.selectedBusiness = business;
  }

  public countBusiness() {
    return this.businessList.length;
    // Add data aggr. for active count.
  }

  public countActiveG() {
    let count = 0;
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.businessList.length; i++) {
      for (this.business of this.businessList) {
        if (this.business.active) {
          count++;
        }
      }
      return count;
    }
  }


  deactivateOrActivateUser(id: number){
    this.userSvc.disableUser(id).subscribe(
      (good)=>{
        this.loadUsers();
        this.selectedUser = null;

      },
      (bad) =>{
        console.error('error in deleting user component')

      }
    )

  }

  deactivateOrActivateBusiness(id: number){
    this.businessSvc.destroy(id).subscribe(
      (good)=>{
        this.loadBusiness();
        this.selectedBusiness = null;

      },
      (bad) =>{
        console.error('error in deleting user component')

      }
    )

  }
}
