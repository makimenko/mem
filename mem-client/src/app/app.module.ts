import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { ApiModule, BASE_PATH } from "./api-generated";
import { environment } from "../environments/environment";
import { MaterialDesignModule } from './material-design/material-design.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ApiModule,
    MaterialDesignModule
  ],
  providers: [
    { provide: BASE_PATH, useValue: environment.servicebaseUrl }
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
