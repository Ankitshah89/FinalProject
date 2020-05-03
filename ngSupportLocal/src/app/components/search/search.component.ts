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
    private busServe: BusinessService
  ) {}

  ngOnInit(): void {
    this.searchResults = [];
    this.route.paramMap.subscribe(
      params => {
      this.keyword = params.get('keyword');
      console.log("Search parameter being used: " +  this.keyword);


    });
    this.allSearch(this.keyword);

  }

  allSearch(keyword){
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
