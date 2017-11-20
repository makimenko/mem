import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { ApiModule, BASE_PATH } from "./api-generated";
import { environment } from "../environments/environment";
import { MaterialDesignModule } from './material-design/material-design.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminModule } from './admin/admin.module';

const appRoutes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ApiModule,
    MaterialDesignModule,
    AdminModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [
    { provide: BASE_PATH, useValue: environment.servicebaseUrl }
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
