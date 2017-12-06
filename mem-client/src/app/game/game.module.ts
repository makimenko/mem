import { NgModule, PipeTransform } from '@angular/core';
import { CommonModule, NgStyle } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialDesignModule } from '../material-design/material-design.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { PipesModule } from '../pipes/pipes.module';
import { MatDialogModule } from '@angular/material';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game/game.component';
import { GameSelectComponent } from './game-select/game-select.component';
import { SessionService } from './services/session.service';
import { ServerFilePipe } from "../pipes/server-file.pipe";

const gameRoutes: Routes = [
  { path: 'gameSelect', component: GameSelectComponent },
  { path: 'game', component: GameComponent }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialDesignModule,
    FlexLayoutModule,
    PipesModule,
    MatDialogModule,    
    RouterModule.forRoot(
      gameRoutes
    )
  ],
  declarations: [
    GameComponent,
    GameSelectComponent
  ],
  exports: [
    RouterModule
  ],
  providers: [
    SessionService,
    ServerFilePipe
  ]
})
export class GameModule { }
