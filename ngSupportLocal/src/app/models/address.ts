import { SplitInterpolation } from '@angular/compiler';

export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  postalCode: string;
  country: string;
  businesId: number;

  constructor(id?: number, street?: string, street2?: string, city?: string, state?: string,
    postalCode?: string, country?: string, businessId?: number){
      this.id = id;
      this.street = street;
      this.street2 = street2;
      this.city = city;
      this.state = state;
      this.postalCode = postalCode;
      this.country = country;
      this.businesId = businessId;
    }

}
