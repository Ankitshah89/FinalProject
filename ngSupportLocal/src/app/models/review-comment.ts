import { NullTemplateVisitor } from '@angular/compiler';

export class ReviewComment {
  id: number;
  reviewId: number;
  content: string;
  createDate: Date;
  inreplyToId: number;
  userId: number;

  constructor(id?: number, reviewId?: number, content?: string, createDate?: Date,
        inreplyToId?: number, userId?: number){

          this.id = id;
          this.reviewId = reviewId;
          this.content = content;
          this.createDate = createDate;
          this.inreplyToId = inreplyToId;
          this.userId = userId;
  }
}
