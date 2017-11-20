import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService, Event } from '../../api-generated';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit {

  event: Event;

  busy: boolean;

  constructor(private route: ActivatedRoute,
    private questionService: QuestionService
  ) { }

  ngOnInit() {
    this.loadEvent();
  }

  loadEvent(): void {
    this.busy = true;
    console.log("Loading...")
    const uuid = this.route.snapshot.paramMap.get('uuid');
    console.log("UUID=" + uuid);
    this.questionService.eventGet(uuid)
      .subscribe(event => {
        this.event = event;
        this.busy = false
      });
  }

  save() {
    console.log("Saving...");
    this.busy = true;
    this.questionService.eventPost(this.event).subscribe(event => {
      this.busy = false;
    }
    );

  }

}
