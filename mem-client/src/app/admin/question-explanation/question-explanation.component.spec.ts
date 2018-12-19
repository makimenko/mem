import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionExplanationComponent } from './question-explanation.component';

describe('QuestionExplanationComponent', () => {
  let component: QuestionExplanationComponent;
  let fixture: ComponentFixture<QuestionExplanationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionExplanationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionExplanationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
