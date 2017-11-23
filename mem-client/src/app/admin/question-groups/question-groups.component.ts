import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';

@Component({
  selector: 'app-question-groups',
  templateUrl: './question-groups.component.html',
  styleUrls: ['./question-groups.component.scss']
})
export class QuestionGroupsComponent implements OnInit {

  @Input() groups: API.Group[];

  constructor() { }

  ngOnInit() {
  }

  newGroup() {
    if (this.groups == undefined) {
      this.groups = [];
    }
    let newGroup: API.Group = { uuid: this.newUuid() };
    this.groups.push(newGroup);
  }

  deleteGroup(group: API.Group) {
    let index = this.groups.indexOf(group);
    this.groups.splice(index, 1);
  }

  newUuid(): string {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }

}
