import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import {  UUID } from '../../utils';

@Component({
  selector: 'app-question-explanation',
  templateUrl: './question-explanation.component.html',
  styleUrls: ['./question-explanation.component.scss']
})
export class QuestionExplanationComponent implements OnInit {

  @Input() question: API.Question;

  constructor() { }

  ngOnInit() {
  }

  createExplanation() {
    this.question.explanation = {};
  }

  deleteExplanation() {
    this.question.explanation = undefined;
  }



  deleteVisualContent(explanation: API.Explanation) {
    explanation.media = undefined;
  }

  onUploadComplete(uploadLocation: API.UploadLocation): void {
    console.log("Image uploaded into: " + uploadLocation.url);
    this.question.explanation.media = uploadLocation;

  }

  isUploadSupported(file: File): boolean {
    return file.type.startsWith("image");
  }

}
