import { User } from './user';

export class ArticleComment {
  id: number;
  articleId: number;
  content: string;
  createDate: Date;
  inreplyToId: number;
  user: User;

  constructor(id?: number, articleId?: number, content?: string, createDate?: Date,
      inreplyToId?: number, user?: User){
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.createDate = createDate;
        this.inreplyToId = inreplyToId;
        this.user = user;
  }
}
