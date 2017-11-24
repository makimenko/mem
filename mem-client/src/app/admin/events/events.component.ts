import { Component, OnInit } from '@angular/core';
import * as API from '../../api-generated';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events: API.Event[];

  busy: boolean = false;

  constructor(public questionService: API.QuestionService) { }

  ngOnInit() {
    this.queryEvents();
  }

  queryEvents() {
    console.log("Finding available events")
    this.busy = true;
    this.questionService.eventsFindGet().subscribe(i => {
      this.events = i;
      this.busy = false;
    });
  }

  delete(event: API.Event) {
    console.log("Deleting event: " + event.uuid);
    this.busy = true;
    this.questionService.eventUuidDelete(event.uuid).subscribe(i => {
      console.log("Deleted, reloading events...");
      this.queryEvents();
    });
  }

}
