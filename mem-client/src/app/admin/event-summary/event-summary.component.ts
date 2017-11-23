import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import { UploadListener } from "../upload-component/upload-listener";

@Component({
  selector: 'app-event-summary',
  templateUrl: './event-summary.component.html',
  styleUrls: ['./event-summary.component.scss']
})
export class EventSummaryComponent implements OnInit, UploadListener {

  @Input() event: API.Event;

  constructor() { }

  ngOnInit() {
  }

  onUploadComplete(uploadLocation: API.UploadLocation): void {
    console.log("Image uploaded into: " + uploadLocation.url);
    this.event.thumbnail = uploadLocation;
  }

  isUploadSupported(file: File): boolean {
    return file.type.startsWith("image");
  }

}
