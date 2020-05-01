import { Business } from './business';
import { SplitInterpolation } from '@angular/compiler';

export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  postalCode: string;
  country: string;
  business: Business;

  constructor(id?: number, street?: string, street2?: string, city?: string, state?: string,
    postalCode?: string, country?: string, business?: Business){
      this.id = id;
      this.street = street;
      this.street2 = street2;
      this.city = city;
      this.state = state;
      this.postalCode = postalCode;
      this.country = country;
      this.business = business;
    }

}
