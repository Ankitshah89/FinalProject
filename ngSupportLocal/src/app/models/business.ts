export class Business {
  id: number;
  name: string;
  descripiton: string;
  phone: string;
  active: boolean;
  imageUrl: string;
  managerId: number;

  constructor(id?: number, name?: string, description?: string, phone?: string, active?: boolean,
              imageUrl?: string, managerId?: number){

    this.id = id;
    this.name = name;
    this.descripiton = description;
    this.phone = phone;
    this.active = active;
    this.imageUrl = imageUrl;
    this.managerId = managerId;
  }
}
