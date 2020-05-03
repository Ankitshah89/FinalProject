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

import { ReviewsComponent } from './components/reviews/reviews.component';
import { BusinessLandingComponent } from './components/business-landing/business-landing.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { AdminLandingComponent } from './components/admin-landing/admin-landing.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


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
    ReviewsComponent,
    BusinessLandingComponent,
    UserLandingComponent,
    CategoryPipe,
    ReviewsComponent,
    LogoutComponent,
    RegisterComponent,
    LoginComponent,
    AdminLandingComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule,
    BrowserAnimationsModule,
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
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
