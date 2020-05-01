import { NullTemplateVisitor } from '@angular/compiler';
import { User } from './user';

export class ReviewComment {
  id: number;
  reviewId: number;
  content: string;
  createDate: Date;
  inreplyToId: number;
  user: User;

  constructor(id?: number, reviewId?: number, content?: string, createDate?: Date,
        inreplyToId?: number, user?: User){

          this.id = id;
          this.reviewId = reviewId;
          this.content = content;
          this.createDate = createDate;
          this.inreplyToId = inreplyToId;
          this.user = user;
  }
}
