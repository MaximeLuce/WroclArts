import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventRegistrationComponent } from './event-registration';

describe('EventRegistrationComponent', () => {
  let component: EventRegistrationComponent;
  let fixture: ComponentFixture<EventRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventRegistrationComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EventRegistrationComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
