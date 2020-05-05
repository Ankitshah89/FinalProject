import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/services/article.service';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/models/article';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss'],
})
export class ArticleDetailComponent implements OnInit {
  article: Article;
  constructor(
    private currentRoute: ActivatedRoute,
    private articleSvc: ArticleService
  ) {}

  ngOnInit(): void {
    this.getArticleById();
  }

  getArticleById() {
    const ArticleIdStr = this.currentRoute.snapshot.paramMap.get('id');
    console.log('Article Id String ' + ArticleIdStr);

    if (ArticleIdStr) {
      const artId = Number.parseInt(ArticleIdStr, 10);
      this.articleSvc.showById(artId).subscribe(
        (yay) => {
          this.article = yay;
          console.log('success retrieving article');
          console.log(this.article.articleComments);
        },
        (nay) => {
          console.log('error retrieving article ' + nay);
        }
      );
    }
  }
}
