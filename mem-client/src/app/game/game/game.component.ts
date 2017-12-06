import { Component, OnInit } from '@angular/core';
import * as API from '../../api-generated';
import { SessionService } from '../services/session.service';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { ServerFilePipe } from "../../pipes/server-file.pipe";

interface GameStats {
  total: number,
  correct: number,
  incorrect: number
}

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  // TODO: Inject
  DELAY_BEFORE_NEXT_QUESTION: number = 3000;

  currentGameStep: API.GameStep;
  currentStepIndex: number = -1;
  progress: number;
  stats: GameStats;

  showingGameEnd: boolean = false;
  showingStepResult: boolean = false;
  lastStepCorrect : boolean = false;
  lastAnswer: API.Answer;

  constructor(
    private sessionService: SessionService,
    private router: Router,
    private sanitizer: DomSanitizer,
    private serverFilePipe: ServerFilePipe
  ) {
    this.nextStep = this.nextStep.bind(this);

  }

  ngOnInit() {
    if (this.sessionService.game == undefined) {
      console.log("No game data, redirecting to game selection");
      this.router.navigate(['/gameSelect']);
    } else {
      this.newGame();
    }
  }

  newGame() {
    console.log("New game");
    this.showingGameEnd = false;
    this.stats = {
      correct: 0,
      incorrect: 0,
      total: this.sessionService.game.gameSteps.length
    };
    this.nextStep();
  }

  nextStep() {
    console.log("Next Step");
    this.showingStepResult = false;
    this.currentStepIndex++;
    this.lastAnswer = undefined;
    if (this.currentStepIndex < this.stats.total) {
      this.currentGameStep = this.sessionService.game.gameSteps[this.currentStepIndex];
      this.progress = ((this.currentStepIndex + 1) / this.stats.total) * 100;
    } else {
      console.log("Game end");
      this.showingGameEnd = true;
    }
  }

  selectAnswer(answer: API.Answer) {
    console.log("selectAnswer:" + answer.name);
    this.showingStepResult = true;
    this.lastAnswer = answer;
    if (answer.expected) {
      console.log("Correct");
      this.stats.correct++;
      this.lastStepCorrect = true;
    } else {
      console.log("Incorrect")
      this.stats.incorrect++;
      this.lastStepCorrect = false;
    }
    setTimeout(this.nextStep, this.DELAY_BEFORE_NEXT_QUESTION);
  }

  getQuestionContentMediaStyle() {
    if (this.currentGameStep.media != undefined && this.currentGameStep.media.url != undefined) {
      let imageUrl = this.serverFilePipe.transform(this.currentGameStep.media)
      console.log("getQuestionContentMediaStyle - Image: " + imageUrl);
      return this.sanitizer.bypassSecurityTrustStyle("background-image:url('" + imageUrl + "')");
    }
  }

}
