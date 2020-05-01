import { User } from './user';

export class Review {
  id: number;
  createDate: Date;
  businessId: number;
  user: User;
  description: string;
  rating: number;
  notification: boolean;

  constructor(id?: number, createDate?: Date, businessId?: number, user?: User,
        description?: string, rating?: number, notification?: boolean){
          this.id = id;
          this.createDate = createDate;
          this.businessId = businessId;
          this.user = user;
          this.description = description;
          this.rating = rating;
          this.notification = notification;
  }
}
