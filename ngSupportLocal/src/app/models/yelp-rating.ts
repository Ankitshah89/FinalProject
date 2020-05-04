import { YelpReview } from './yelp-review';
export class YelpRating {
  rating: string;
  yelpReviews: YelpReview;

  constructor(rating?: string, yelpReviews?: YelpReview) {
    this.rating = rating;
    this.yelpReviews = yelpReviews;
  }
}
