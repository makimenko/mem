import { Component, OnInit, Input } from '@angular/core';
import * as API from '../../api-generated';
import { GameComponent } from '../game/game.component';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.scss']
})
export class StepComponent implements OnInit {

  @Input() gameStep: API.GameStep;

  @Input() gameComponent : GameComponent;
  
  constructor() { }

  ngOnInit() {
  }

  select(answer: API.Answer) {
    console.log("Answered:" + answer.name);
    if (answer.expected) {
      console.log("Correct");
    } else {
      console.log("Incorrect")
    }
    this.gameComponent.nextStep();
  }

}
