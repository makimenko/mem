import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import {  UUID } from "../../utils";
import { UploadListener } from "../../utils";

@Component({
  selector: 'app-group-visual-content',
  templateUrl: './group-visual-content.component.html',
  styleUrls: ['./group-visual-content.component.scss']
})
export class GroupVisualContentComponent implements OnInit, UploadListener {

  @Input() group: API.Group;

  constructor() { }

  ngOnInit() {
  }

  deleteVisualContent(visualContent: API.VisualContent) {
    let index = this.group.visualContents.indexOf(visualContent);
    if (index < 0) {
      throw Error("Visual Content could not be deleted, because it was not found!");
    }
    this.group.visualContents.splice(index, 1);
  }

  onUploadComplete(uploadLocation: API.UploadLocation): void {
    console.log("Image uploaded into: " + uploadLocation.url);

        if (this.group.visualContents == undefined) {
      this.group.visualContents = [];
    }
    let visualContent = {} as API.VisualContent;
    visualContent.uuid = UUID.randomUUID();
    visualContent.media =  uploadLocation;
    this.group.visualContents.push(visualContent);

  }

  isUploadSupported(file: File): boolean {
    return file.type.startsWith("image");
  }

}
