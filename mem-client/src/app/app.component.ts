import { Component, OnInit } from '@angular/core';
import { QuestionService, Event } from "./api-generated";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [QuestionService]
})
export class AppComponent implements OnInit {
  title = 'app';

  profiles: Event[];

  constructor(private questionService: QuestionService) { }

  ngOnInit() {
    this.loadProfiles();
  }

  loadProfiles() {
    this.questionService.eventsGet().subscribe(i => this.profiles = i);
  }

}
