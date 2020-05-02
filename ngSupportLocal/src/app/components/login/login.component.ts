import { UserService } from 'src/app/services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

user : User = new User();

  constructor(
    private router: Router,
    private auth: AuthService,
    private userService : UserService
  ) {}

  isAdmin = false;

  ngOnInit() {}

  login(loginForm: NgForm) {
    const user: User = new User();
    user.email = loginForm.value.email;
    user.password = loginForm.value.password;

  // let newUser : User = null;
  //   console.log(this.newUser.role);


    this.auth.login(user.email, user.password).subscribe(
      next => {
        console.log('This is in Login with next information: ');
        console.log(next);


        console.log(user.role);
       if (next.role === 'Admin'){
          this.router.navigateByUrl('/admin-landing');
        }
        else if(next.role === 'Business'){
          this.router.navigateByUrl('/businesses');
        }
        else{
          this.router.navigateByUrl('/user-landing');
        }
      },
      error => {
        this.router.navigateByUrl('/login');
      }
    );
  }

  // login() {
  //   this.auth.login(this.user.username, this.user.password).subscribe(
  //     next => {
  //       this.userData.setLoggedIn();
  //     },
  //     error => {
  //       console.error(error);
  //       console.error('LoginComponent.login(): error logging in.');
  //     },
  //     () => {
  //       this.userService.index().subscribe(
  //         good => {
  //           this.user = good;
  //           this.userData.setUser(good);
  //           this.userData.setUserRole(this.userData.user.role);
  //           if (!this.user.role) {
  //             this.router.navigateByUrl('/patient-registration');
  //           }
  //           if (this.user.role.toLowerCase() === 'ems') {
  //             this.router.navigateByUrl('/emt-view');
  //           }
  //           if (this.user.role.toLowerCase() === 'user') {
  //             this.router.navigateByUrl('/app/tabs/medications');
  //           }
  //           if (this.user.role.toLowerCase() === 'physician') {
  //             this.router.navigateByUrl('/patient-list');
  //           }
  //           if (this.user.role.toLowerCase() === 'admin') {
  //             this.router.navigateByUrl('/admin-dashboard');
  //           }
  //         },
  //         error => {
  //           console.error(error);
  //           console.error('LoginComponent.login(): error logging in.');
  //         }
  //       );
  //     }
  //   );
  // }
}
