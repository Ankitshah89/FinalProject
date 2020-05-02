import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.scss'],
})
export class UserLandingComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  myFunction() {
    var dots = document.getElementById('dots');
    var moreText = document.getElementById('more');
    var btnText = document.getElementById('myBtn');

    if (dots.style.display === 'none') {
      dots.style.display = 'inline';
      btnText.innerHTML = 'Read more';
      moreText.style.display = 'none';
    } else {
      dots.style.display = 'none';
      btnText.innerHTML = 'Read less';
      moreText.style.display = 'inline';
    }
  }
}
