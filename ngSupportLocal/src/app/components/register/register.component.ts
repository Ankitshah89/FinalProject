import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { AddressService } from 'src/app/services/address.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/models/address';
import { Business } from 'src/app/models/business';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userRole = 'User';

  constructor(
    private router: Router,
    private auth: AuthService,
    private businessSvc: BusinessService,
    private addressSvc: AddressService
  ) {}

  ngOnInit(): void {}

  register(userForm: NgForm) {
    const formData = userForm.value;

    const user: User = new User(
      0,
      formData.firstName,
      formData.lastName,
      formData.email,
      formData.password,
      formData.role,
      formData.phone,
      formData.userImageUrl
    );

    const address: Address = new Address(
      0,
      formData.street,
      formData.street2,
      formData.city,
      formData.state,
      formData.postalCode
    );

    const business: Business = new Business(
      0,
      formData.name,
      formData.description,
      formData.businessPhone,
      formData.imageUrl
    );

    business.manager = user;
    user.role = this.userRole;
    address.business = business;

    console.log(user);
    console.log(business);
    console.log(address);

    this.auth.register(user).subscribe(
      (data) => {
        console.log('RegisterComponent.register(): user registered.');
        this.auth.login(user.email, user.password).subscribe(
          (next) => {
            console.log(
              'RegisterComponent.register(): user logged in, routing to /userprofile.'
            );

            if (this.userRole == 'Business') {
              this.businessSvc.create(business).subscribe(
                (good) => {
                  console.log(
                    'RegisterComponent.register(): business registered.'
                  );
                  console.log(good);

                  address.business = good;
                  this.addressSvc.createAddress(address).subscribe(
                    (yes) => {
                      console.log(
                        'RegisterComponent.register(): address registered.'
                      );
                      console.log(yes);
                      userForm.reset();
                      this.router.navigateByUrl('/businesses');
                    },
                    (no) => {
                      console.error(
                        'RegisterComponent.register(): error creating address.'
                      );
                      console.error(no);
                    }
                  );
                },
                (bad) => {
                  console.error(
                    'RegisterComponent.register(): error creating business.'
                  );
                  console.error(bad);
                }
              );
            }else{
              this.router.navigateByUrl('/user-landing');
              userForm.reset();
            }
          },
          (error) => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      },
      (err) => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
      }
    );
  }

}
