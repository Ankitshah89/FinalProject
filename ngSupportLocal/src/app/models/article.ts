import { User } from './user';
import { Business } from './business';
import { ArticleComment } from './article-comment';

export class Article {
  id: number;
  user: User;
  business: Business;
  title: string;
  content: string;
  createAt: Date;
  active: boolean;
  imageUrl: string;
  articleComments: ArticleComment[];

  constructor(
    id?: number,
    user?: User,
    business?: Business,
    title?: string,
    content?: string,
    createAt?: Date,
    active?: boolean,
    imageUrl?: string,
    articleComments?: ArticleComment[]
  ) {
    this.id = id;
    this.user = user;
    this.business = business;
    this.title = title;
    this.content = content;
    this.createAt = createAt;
    this.active = active;
    this.imageUrl = imageUrl;
    this.articleComments = articleComments ? articleComments : [];
  }
}
