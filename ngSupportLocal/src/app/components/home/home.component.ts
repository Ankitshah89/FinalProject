import { logging } from 'protractor';
import { Article } from './../../models/article';
import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  artList = [];
  constructor(private artServ: ArticleService) {}

  ngOnInit(): void {
    this.recentArticles();
  }

  //Function for front Cards
  public recentArticles() {
    var newArticle = [];
    this.artServ.index().subscribe(
      (results) => {
        newArticle = results;
        console.log(newArticle);
        this.loadRecentArticles(newArticle);
      },
      (error) => {
        console.log('Loading recent articles failed');
        console.log(error);
      }
    );
  }

  public loadRecentArticles(newArticle: Article[]) {
    for (var i = 0; i < 4; i++) {
      if (newArticle.length > 0) {
        var art = newArticle.pop();
        console.log(art.business);

        this.artList.push(art);
        console.log(newArticle);
      }
    }
  }
}

// new WOW().init();

// public spliceArticleContent(content: string){
//   return content.slice(0,101);
// }

//Function to grab Articles for Carousel //Stretch Goal
