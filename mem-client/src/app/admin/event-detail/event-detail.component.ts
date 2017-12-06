import { Component, OnInit, Optional, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as API from '../../api-generated';
import { UploadListener } from "../../utils";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit {

  event: API.Event;

  busy: boolean;

  constructor(private route: ActivatedRoute,
    private questionService: API.QuestionService
  ) { }

  ngOnInit() {
    this.loadEvent();
  }

  loadEvent(): void {
    console.log("Loading...")
    const uuid = this.route.snapshot.paramMap.get('uuid');
    console.log("UUID=" + uuid);

    if (uuid == "new") {
      console.log("Creating new event");
      this.event = {};
    } else {
      console.log("Retrieving event from server");
      this.busy = true;
      this.questionService.eventUuidGet(uuid)
        .subscribe(event => {
          this.event = event;
          this.busy = false
        });
    }
  }

  saveEvent() {
    console.log("Save event");
    console.log(this.event);
    this.busy = true;
    this.questionService.eventPost(this.event).subscribe(event => {
      if (this.event.uuid == undefined || this.event.uuid != event.uuid) {
        this.event = event;
      }
      console.log("Event stored: " + this.event.uuid);
      this.busy = false;
    });
  }


}
