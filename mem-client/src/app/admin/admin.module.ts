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
import { GroupVisualContentComponent } from './group-visual-content/group-visual-content.component';
import { MatDialogModule } from '@angular/material';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AutoFocusDirective } from './question-answers/auto-focus.directive';
import { QuestionExplanationComponent } from '../admin/question-explanation/question-explanation.component';

const adminRoutes: Routes = [
  { path: 'admin', component: AdminHomeComponent 
   ,children: [
    { path: 'events', component: EventsComponent },
    { path: 'event-detail/:uuid', component: EventDetailComponent }
   ]
  }
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
      adminRoutes
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
    QuestionAnswersComponent,
    GroupVisualContentComponent,
    ConfirmDialogComponent,
    AdminHomeComponent,
    AutoFocusDirective,
    QuestionExplanationComponent
  ],
  entryComponents: [
    ConfirmDialogComponent
  ],
  exports: [
    RouterModule
  ]
})
export class AdminModule { }
