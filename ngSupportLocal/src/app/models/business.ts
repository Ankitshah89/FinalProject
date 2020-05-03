import { User } from './user';
import { Address } from './address';

export class Business {
  id: number;
  name: string;
  descripiton: string;
  phone: string;
  imageUrl: string;
  manager: User;
  active: boolean;
  address: Address;

  constructor(id?: number, name?: string, description?: string, phone?: string,
              imageUrl?: string, manager?: User,active?: boolean, address?: Address){

    this.id = id;
    this.name = name;
    this.descripiton = description;
    this.phone = phone;
    this.active = active;
    this.imageUrl = imageUrl;
    this.manager = manager;
    this.address = address;
  }
}
