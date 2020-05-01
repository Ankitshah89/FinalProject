
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss'],
})
export class NavBarComponent implements OnInit {

  constructor(
    private router: Router,
    private auth: AuthService,
  ) {}

  ngOnInit(): void {}


  userLogInCheck() {
    return this.auth.getCredentials();
  }

  logout() {
    this.auth.logout();
    this.ngOnInit();
    this.router.navigateByUrl('/home');
  }



}
