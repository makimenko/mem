import { Component, OnInit, Optional, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as API from '../../api-generated';
import { UploadListener } from "../upload-component/upload-listener";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit, UploadListener {

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

  save() {
    console.log("Save event");
    this.busy = true;
    this.questionService.eventPost(this.event).subscribe(event => {
      this.busy = false;
      this.event = event;
      console.log("Event stored: " + this.event.uuid);
    });
  }

  onUploadComplete(uploadLocation: API.UploadLocation): void {
    console.log("Image uploaded into: " + uploadLocation.url);
    this.event.thumbnail = uploadLocation;
  }

  isUploadSupported(file: File): boolean {
    return file.type.startsWith("image");
  }

  newGroup() {
    if (this.event.groups == undefined) {
      this.event.groups = [];
    }
    let newGroup: API.Group = {uuid:this.newUuid()};
    this.event.groups.push(newGroup);
  }

  deleteGroup(group: API.Group) {
    let index = this.event.groups.indexOf(group);
    this.event.groups.splice(index, 1);
  }

  newUuid() : string{
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

}
