import { User } from './user';
import { Business } from './business';

export class Review {
  id: number;
  createDate: Date;
  business: Business;
  user: User;
  description: string;
  rating: number;
  notification: boolean;

  constructor(id?: number, createDate?: Date, business?: Business, user?: User,
        description?: string, rating?: number, notification?: boolean){
          this.id = id;
          this.createDate = createDate;
          this.business = business;
          this.user = user;
          this.description = description;
          this.rating = rating;
          this.notification = notification;
  }
}
