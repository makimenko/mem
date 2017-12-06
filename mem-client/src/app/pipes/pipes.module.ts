import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServerFilePipe } from './server-file.pipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    ServerFilePipe
  ],
  exports: [
    ServerFilePipe
  ]
})
export class PipesModule { }
