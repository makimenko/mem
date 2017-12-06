import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import {  UUID } from "../../utils";

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.scss']
})
export class QuestionListComponent implements OnInit {

  @Input() group: API.Group;

  constructor() {
  }

  ngOnInit() {
  }

  newQuestion() {
    if (this.group.questions == undefined) {
      this.group.questions = [];
    }
    let question = {} as API.Question;
    question.uuid = UUID.randomUUID();
    this.group.questions.push(question);
  }

  deleteQuestion(question: API.Question) {
    let index = this.group.questions.indexOf(question);
    if (index < 0) {
      throw Error("Question could not be deleted, because it was not found!");
    }
    this.group.questions.splice(index, 1);
  }


}
