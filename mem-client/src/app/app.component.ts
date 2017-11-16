import { Component, OnInit } from '@angular/core';
import { DefaultService, Profile } from "./api-generated";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [DefaultService]
})
export class AppComponent implements OnInit {
  title = 'app';

  profiles: Profile[];

  constructor(private defaultService: DefaultService) { }

  ngOnInit() {
    this.loadProfiles();
  }

  loadProfiles() {
    this.defaultService.getAllProfiles().subscribe(i => this.profiles = i);
  }

}
