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

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserComponent },
  { path: 'user-landing', component: UserLandingComponent },
  { path: 'business', component: BusinessComponent },
  { path: 'business-landing', component: BusinessLandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'businesses', component: BusinessComponent },
  { path: 'articles', component: ArticlesComponent },
  { path: 'reviews', component: ReviewsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
