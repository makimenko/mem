import { Component, OnInit, Input, Renderer, ElementRef, Directive, ViewChild } from '@angular/core';
import * as API from '../../api-generated';
import { UUID } from '../../utils';

@Component({
  selector: 'app-question-answers',
  templateUrl: './question-answers.component.html',
  styleUrls: ['./question-answers.component.scss']
})
export class QuestionAnswersComponent implements OnInit {

  @Input() question: API.Question;


  constructor() { }

  ngOnInit() {

  }

  newAnswer() {
    if (this.question.answers == undefined) {
      this.question.answers = [];
    }
    let lastAnswer = this.question.answers[this.question.answers.length - 1];
    if (this.question.answers.length==0 || lastAnswer != undefined && lastAnswer.name != undefined) {
      let answer = {} as API.Answer;
      answer.uuid = UUID.randomUUID();
      this.question.answers.push(answer);
    }
  }

  deleteAnswer(answer: API.Answer) {
    let index = this.question.answers.indexOf(answer);
    if (index < 0) {
      throw Error("Answer could not be deleted, because it was not found!");
    }
    this.question.answers.splice(index, 1);
  }

  onBackspace(answer: API.Answer) {
    if (answer.name == undefined || answer.name == "") {
      this.deleteAnswer(answer);
    }
  }

  onKeyPress(event: KeyboardEvent) {
    console.log(event);
    if (event.key == "Enter") {

    }
  }

}
