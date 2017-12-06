import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupVisualContentComponent } from './group-visual-content.component';

describe('GroupVisualContentComponent', () => {
  let component: GroupVisualContentComponent;
  let fixture: ComponentFixture<GroupVisualContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupVisualContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupVisualContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
