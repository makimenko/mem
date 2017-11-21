import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageUploadComponentComponent } from './image-upload-component.component';

describe('ImageUploadComponentComponent', () => {
  let component: ImageUploadComponentComponent;
  let fixture: ComponentFixture<ImageUploadComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageUploadComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageUploadComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
