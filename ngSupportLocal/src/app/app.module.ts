import { UserService } from './services/user.service';
import { ReviewService } from './services/review.service';
import { ReviewCommentService } from './services/review-comment.service';
import { PreferenceService } from './services/preference.service';
import { BusinessService } from './services/business.service';
import { AuthService } from './services/auth.service';
import { ArticleService } from './services/article.service';
import { ArticleCommentService } from './services/article-comment.service';
import { AddressService } from './services/address.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BusinessComponent } from './components/business/business.component';
import { UserComponent } from './components/user/user.component';
import { SearchComponent } from './components/search/search.component';
import { HomeComponent } from './components/home/home.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { LeftSideBarComponent } from './components/left-side-bar/left-side-bar.component';
import { RightSideBarComponent } from './components/right-side-bar/right-side-bar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CategoryPipe } from './pipes/category.pipe';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    BusinessComponent,
    UserComponent,
    SearchComponent,
    HomeComponent,
    ArticlesComponent,
    LeftSideBarComponent,
    RightSideBarComponent,
    CategoryPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [
    AddressService,
    ArticleCommentService,
    ArticleService,
    AuthService,
    BusinessService,
    PreferenceService,
    ReviewCommentService,
    ReviewService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
