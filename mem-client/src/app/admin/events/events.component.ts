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

  delete(event:Event )  {
    console.log("Deleting event: "+event.uuid);
    this.questionService.eventUuidDelete(event.uuid).subscribe(i => {
      console.log("Deleted, reloading events...");
      this.loadEvents();
    }
    );
  }
  
}
