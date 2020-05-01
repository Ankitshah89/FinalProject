import { Business } from './../models/business';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Address } from './../models/address';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {

  }
  private url = environment.baseUrl + 'api/addresses/';

  public index() {
    return this.http.get<Address[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Index list of addresses could not be found');

        })
      );
  }

  public addressId(id: number) {
    return this.http.get<Address>(this.url + id)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not find address by index: ' + id)

        })
      );
  }

  public createAddress(newAddress: Address, business: Business) {
    newAddress.businessId = business.id;

    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()) {
      return this.http.post<any>(this.url, newAddress, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Could not create new Address' + err);
        })
      )
    } else {
      this.router.navigateByUrl('/home');

    }
  }

  //Delete
  public deleteAddress(id: number){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if(this.authService.checkLogin()) {
      return this.http.delete<any>(this.url + id, httpOptions)
      .pipe(
        catchError((err : any) =>{
        console.log(err);
        return throwError('Could not delete address with id: ' + id);
        })
      );
    }
  }

  //Update
  public updateAddress(address: Address){
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    if (this.authService.checkLogin()){
      return this.http.put<Address>(this.url + address.id, httpOptions)
      .pipe(
        catchError((err: any) =>{
          console.log(err);
          return throwError('Could not update address with id: ' + address.id);

        })
      )
    };
  }


}
