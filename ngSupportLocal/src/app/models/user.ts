export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  role: string;
  phone: string;
  createdAt: Date;
  active: boolean;

  constructor(id?: number, firstName?: string, lastName?: string, email?: string, password?: string,
      role?: string, phone?: string, createdAt?: Date, active?: boolean){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
        this.active = active;
  }
}
