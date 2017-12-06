import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';

@Component({
  selector: 'app-question-editor',
  templateUrl: './question-editor.component.html',
  styleUrls: ['./question-editor.component.scss']
})
export class QuestionEditorComponent implements OnInit {

  @Input() question: API.Question;

  constructor() { }

  ngOnInit() {
  }

}
