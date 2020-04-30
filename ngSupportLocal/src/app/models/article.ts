export class Article {
  id: number;
  userId: number;
  businessId: number;
  title: string;
  content: string;
  createAt: Date;
  imageUrl: string;

  constructor(id?: number, userId?: number, businessId?: number, title?: string, content?: string,
            createAt?: Date, imageUrl?: string){
      this.id = id;
      this.userId = userId;
      this.businessId = businessId;
      this.title = title;
      this.content = content;
      this.createAt = createAt;
      this.imageUrl = imageUrl;
  }
}
