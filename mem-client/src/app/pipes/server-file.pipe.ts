import { Pipe, PipeTransform, Optional, Inject } from '@angular/core';
import * as API from '../api-generated';

@Pipe({
  name: 'serverFile'
})
export class ServerFilePipe implements PipeTransform {

  constructor( @Optional() @Inject(API.BASE_PATH) private basePath: string) {
  }

  transform(value: API.UploadLocation): string {
    if (value != undefined && value.url != undefined) {
      return this.basePath + value.url;
    } else {
      return undefined;
    }
  }

}
