import { ArticleComment } from './article-comment';
import { Article } from './article';
import { ReviewComment } from './review-comment';
import { Business } from './business';
import { Preference } from './preference';
import { Review } from './review';

export class User {
  id: number;//
  firstName: string;//
  lastName: string;//
  email: string;//
  password: string;//
  role: string;//
  phone: string;//
  createdAt: Date;//
  active: boolean;//
  reviews: Review[];//
  reviewComments: ReviewComment[]; //
  articles: Article[]; //
  businesses: Business[]; //
  articleComments: ArticleComment[]; //
  favoriteBusinesses: Business[];//
  preferences: Preference[]; //

  constructor(id?: number,
              firstName?: string,
              lastName?: string,
              email?: string,
              password?: string,
              role?: string,
              phone?: string,
              createdAt?: Date,
              active?: boolean,
              favoriteBusinesses?: Business[],
              reviews?: Review[],
              reviewComments?: ReviewComment[],
              articles?: Article[],
              businesses?: Business[],
              articleComments?: ArticleComment[],
              preferences?: Preference[]){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
        this.active = active;
        this.favoriteBusinesses = favoriteBusinesses;
        this.reviews = reviews;
        this.reviewComments = reviewComments;
        this.articles = articles;
        this.businesses = businesses;
        this.articleComments = articleComments;
        this.preferences = preferences;
  }
}
