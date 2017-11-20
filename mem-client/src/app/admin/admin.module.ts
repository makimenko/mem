import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventsComponent } from './events/events.component';
import { RouterModule, Routes } from '@angular/router';
import { MaterialDesignModule } from '../material-design/material-design.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { FormsModule } from '@angular/forms';

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
    RouterModule.forRoot(
      appRoutes
    )
  ],
  declarations: [
    EventsComponent,
    EventDetailComponent
  ],
  exports: [
    RouterModule
  ]
})
export class AdminModule { }
