import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { ApiModule, BASE_PATH } from "./api";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ApiModule
  ],
  providers: [
      { provide: BASE_PATH, useValue: 'http://localhost:8080' }
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
