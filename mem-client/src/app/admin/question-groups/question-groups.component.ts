import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import { UUID } from "../../utils";

@Component({
  selector: 'app-question-groups',
  templateUrl: './question-groups.component.html',
  styleUrls: ['./question-groups.component.scss']
})
export class QuestionGroupsComponent implements OnInit {

  @Input() event: API.Event;

  constructor() { }

  ngOnInit() {
  }

  newGroup() {
    if (this.event.groups == undefined) {
      this.event.groups = [];
    }
    let group = {} as API.Group;
    group.uuid = UUID.randomUUID();
    this.event.groups.push(group);
  }

  deleteGroup(group: API.Group) {
    let index = this.event.groups.indexOf(group);
    if (index < 0) {
      throw Error("Group could not be deleted, because it was not found!");
    }
    this.event.groups.splice(index, 1);
  }

}
