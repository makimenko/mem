import { ServerFilePipe } from './server-file.pipe';

describe('ServerFilePipe', () => {
  it('create an instance', () => {
    const pipe = new ServerFilePipe("");
    expect(pipe).toBeTruthy();
  });
});
