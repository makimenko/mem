import { Component, OnInit } from '@angular/core';
import { QuestionService, Event } from '../../api-generated/index';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events: Event[];

  constructor(public questionService: QuestionService) { }

  ngOnInit() {
    this.loadEvents();
  }

  loadEvents() {
    this.questionService.eventsGet().subscribe(i => this.events = i);
  }
  
}
