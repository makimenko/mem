import { Component, OnInit } from '@angular/core';
import * as API from '../../api-generated';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-game-select',
  templateUrl: './game-select.component.html',
  styleUrls: ['./game-select.component.scss']
})
export class GameSelectComponent implements OnInit {

  constructor(
    private gameService: API.GameService,
    private router: Router,
    private sessionService: SessionService
  ) { }

  ngOnInit() {
  }

  simpleGame() {
    console.log("New game");
    let option: API.GameOption = {
      gameType: API.GameType.Simple
    }
    this.gameService.gameNewPost(option).subscribe(game => {
      console.log("Game created");
      console.log(game);
      if (game != undefined && game.gameSteps != undefined && game.gameSteps.length > 0) {
        this.sessionService.game = game;
        this.router.navigate(['/game']);
      } else {
        console.log("No game content!");
      }
    });
  }

}
