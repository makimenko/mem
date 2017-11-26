import { Injectable } from '@angular/core';
import * as API from '../../api-generated';

@Injectable()
export class SessionService {

  game: API.Game;

  constructor() { }

}
