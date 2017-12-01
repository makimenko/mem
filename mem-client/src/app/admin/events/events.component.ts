import { Component, OnInit } from '@angular/core';
import * as API from '../../api-generated';
import { MatDialog } from '@angular/material';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events: API.Event[];

  busy: boolean;

  constructor(
    public questionService: API.QuestionService,
    public dialog: MatDialog
  ) { }

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

    let dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe(confirmed => {
      console.log('The dialog was closed. confirmed=' + confirmed);
      if (confirmed) {
        this.busy = true;
        this.questionService.eventUuidDelete(event.uuid).subscribe(i => {
          console.log("Deleted, reloading events...");
          this.queryEvents();
        });
      }
    });
  }

}
