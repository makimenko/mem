import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadComponentComponent } from './upload-component.component';

describe('UploadComponentComponent', () => {
  let component: UploadComponentComponent;
  let fixture: ComponentFixture<UploadComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
