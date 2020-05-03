import { AddressService } from 'src/app/services/address.service';
import { BusinessService } from 'src/app/services/business.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  searchResults = [];
  unfilterList = [];
  keyword = '';

  constructor(
    private route: ActivatedRoute,
    private busServe: BusinessService,
    private addServe: AddressService
  ) {}

  ngOnInit(): void {
    this.searchResults = [];
    this.route.paramMap.subscribe(
      params => {
      this.keyword = params.get('keyword');
      console.log("Search parameter being used: " +  this.keyword);
      this.generalSearch(params.get('keyword'));


    });
    // this.allBussSearch(this.keyword);


  }

  generalSearch(keyword){
    this.addServe.generalSearch(keyword).subscribe(
      results => {
        this.searchResults = results;
        console.log(this.searchResults);

      }, err => {
        console.log(err);
        console.log('General search results yielded no results');


      }
    )
  }

  allBussSearch(keyword){
    this.searchByZip(keyword);
    this.searchByDescription(keyword);
    this.searchByName(keyword);

  }

  searchByName(keyword:string) {
    this.busServe.searchName(keyword).subscribe(
      data => {
        this.searchResults.push(data);
        console.log(data);
      }, error =>{
        console.log('No search results from Search.compoent searchByName');

      });
  }
  searchByDescription(keyword:string) {
    this.busServe.searchDescription(keyword).subscribe(
      data => {
        this.searchResults.push(data);
        console.log(data);
      }, error =>{
        console.log('No search results from Search.compoent searchByDescription');

      });
  }
  searchByZip(keyword:string) {
    this.busServe.searchZip(keyword).subscribe(
      data => {
        this.searchResults.push(data);
        console.log(data);

      }, error =>{
        console.log('No search results from Search.compoent searchByZip');

      });
  }
}
