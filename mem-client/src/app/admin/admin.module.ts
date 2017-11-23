import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventsComponent } from './events/events.component';
import { RouterModule, Routes } from '@angular/router';
import { MaterialDesignModule } from '../material-design/material-design.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { FormsModule } from '@angular/forms';
import { UploadComponentComponent } from './upload-component/upload-component.component';
import { PipesModule } from '../pipes/pipes.module';
import { QuestionGroupsComponent } from './question-groups/question-groups.component';
import { EventSummaryComponent } from './event-summary/event-summary.component';
import { QuestionListComponent } from './question-list/question-list.component';
import { QuestionEditorComponent } from './question-editor/question-editor.component';
import { QuestionAnswersComponent } from './question-answers/question-answers.component';

const appRoutes: Routes = [
  { path: 'events', component: EventsComponent },
  { path: 'event-detail/:uuid', component: EventDetailComponent }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialDesignModule,
    FlexLayoutModule,
    PipesModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  declarations: [
    EventsComponent,
    EventDetailComponent,
    UploadComponentComponent,
    QuestionGroupsComponent,
    EventSummaryComponent,
    QuestionListComponent,
    QuestionEditorComponent,
    QuestionAnswersComponent
  ],
  exports: [
    RouterModule
  ]
})
export class AdminModule { }
