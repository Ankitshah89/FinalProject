
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss'],
})
export class NavBarComponent implements OnInit {
  keyword = null;
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

  search(keyword: string){

    console.log(keyword);

    this.router.navigate(['search/'+keyword]);
    // this.router.navigateByUrl()
    console.log('Navigating to search');

    this.keyword = null;
  }//"search(keyword)"


}
