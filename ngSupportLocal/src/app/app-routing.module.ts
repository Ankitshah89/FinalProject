import { SearchComponent } from './components/search/search.component';
import { LeftSideBarComponent } from './components/left-side-bar/left-side-bar.component';
import { UserComponent } from './components/user/user.component';
import { BusinessComponent } from './components/business/business.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { BusinessLandingComponent } from './components/business-landing/business-landing.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { AdminLandingComponent } from './components/admin-landing/admin-landing.component';
import { BusinessesComponent } from './components/businesses/businesses.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
const routes: Routes = [
  { path: 'search/:keyword', component: SearchComponent },
  { path: 'home', component: HomeComponent },
  { path: 'user/:id', component: UserComponent }, //Non-owner user view
  { path: 'user-landing', component: UserLandingComponent }, //Logged in user
  { path: 'business', component: BusinessComponent }, //Non-owner view
  { path: 'business-landing', component: BusinessLandingComponent }, //Logged in Business
  { path: 'admin-landing', component: AdminLandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'businesses', component: BusinessesComponent },
  { path: 'articles', component: ArticlesComponent },
  { path: 'article-detail/:id', component: ArticleDetailComponent },
  { path: 'reviews', component: ReviewsComponent },
  { path: 'articles/:id', component: ArticlesComponent },
  { path: 'business/:id/articles', component: ArticlesComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
