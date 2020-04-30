export class ArticleComment {
  id: number;
  articleId: number;
  content: string;
  createDate: Date;
  inreplyToId: number;
  userId: number;

  constructor(id?: number, articleId?: number, content?: string, createDate?: Date,
      inreplyToId?: number, userId?: number){
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.createDate = createDate;
        this.inreplyToId = inreplyToId;
        this.userId = userId;
  }
}
