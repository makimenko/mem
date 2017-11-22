import { Pipe, PipeTransform, Optional, Inject } from '@angular/core';
import { BASE_PATH, UploadLocation } from '../api-generated';

@Pipe({
  name: 'serverFile'
})
export class ServerFilePipe implements PipeTransform {

  constructor( @Optional() @Inject(BASE_PATH) private basePath: string) {
  }

  transform(value: UploadLocation): string {
    if (value != undefined && value.url != undefined) {
      return this.basePath + value.url;
    } else {
      return undefined;
    }
  }

}
