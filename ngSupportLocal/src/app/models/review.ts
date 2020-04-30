export class Review {
  id: number;
  createDate: Date;
  businessId: number;
  userId: number;
  description: string;
  rating: number;
  notification: boolean;

  constructor(id?: number, createDate?: Date, businessId?: number, userId?: number,
        description?: string, rating?: number, notification?: boolean){
          this.id = id;
          this.createDate = createDate;
          this.businessId = businessId;
          this.userId = userId;
          this.description = description;
          this.rating = rating;
          this.notification = notification;
  }
}
