import { User } from './user';

export class Business {
  id: number;
  name: string;
  descripiton: string;
  phone: string;
  imageUrl: string;
  manager: User;
  active: boolean;

  constructor(id?: number, name?: string, description?: string, phone?: string,
              imageUrl?: string, manager?: User,active?: boolean){

    this.id = id;
    this.name = name;
    this.descripiton = description;
    this.phone = phone;
    this.active = active;
    this.imageUrl = imageUrl;
    this.manager = manager;
  }
}
