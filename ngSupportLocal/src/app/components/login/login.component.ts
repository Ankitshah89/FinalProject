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
accessDenied = false;
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


        console.log(next.role);
       if (next.role === 'Admin'){
          this.router.navigateByUrl('/admin-landing');
        }
        else if(next.role === 'Business'){
          this.router.navigateByUrl('/business-landing');
        }
        else{
          this.router.navigateByUrl('/user-landing');
        }
      },
      error => {
        this.accessDenied = true;
        this.router.navigateByUrl('/login');
      }
    );
  }


}
