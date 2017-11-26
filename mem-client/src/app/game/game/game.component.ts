import { Component, OnInit } from '@angular/core';
import * as API from '../../api-generated';
import { SessionService } from '../services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  currentGameStep: API.GameStep;
  currentStepIndex: number = -1;
  totalSteps: number;
  progress: number;

  constructor(
    private sessionService: SessionService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.sessionService.game == undefined) {
      console.log("No game data, redirecting to game selection");
      this.router.navigate(['/gameSelect']);
    } else {
      console.log("Starting from first step");
      this.totalSteps = this.sessionService.game.gameSteps.length;
      this.nextStep();
    }
  }

  nextStep() {
    this.currentStepIndex++;
    if (this.currentStepIndex < this.totalSteps) {
      this.currentGameStep = this.sessionService.game.gameSteps[this.currentStepIndex];
      this.updateProgress();
    } else {
      console.log("Game end")
    }

  }

  updateProgress() {
    this.progress = ((this.currentStepIndex + 1) / this.totalSteps) * 100;
  }

}
