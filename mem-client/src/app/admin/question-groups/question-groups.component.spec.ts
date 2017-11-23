import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionGroupsComponent } from './question-groups.component';

describe('QuestionGroupsComponent', () => {
  let component: QuestionGroupsComponent;
  let fixture: ComponentFixture<QuestionGroupsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionGroupsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionGroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
