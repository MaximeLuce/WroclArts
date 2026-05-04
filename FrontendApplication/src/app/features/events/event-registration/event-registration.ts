import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BehaviorSubject, switchMap } from 'rxjs';
import { EventService } from '../../../services/events';
import { EventRegistrationService } from '../../../services/event-registration';

@Component({
  selector: 'app-event-registration',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-registration.html',
  styleUrl: './event-registration.scss',
})
export class EventRegistrationComponent {
  private eventService = inject(EventService);
  private registrationService = inject(EventRegistrationService);

  private refreshTrigger$ = new BehaviorSubject<void>(undefined);

  events$ = this.refreshTrigger$.pipe(switchMap(() => this.eventService.getAll()));

  refresh() {
    this.refreshTrigger$.next();
  }

  subscribe(event: any) {
    if (!confirm('Are you sure you want to register for this event?')) return;

    this.registrationService.registerToEvent(event.eventId).subscribe({
      next: (updatedEvent) => {
        alert(`Success! You are now registered for: ${updatedEvent.title}`);

        // UI-update
        event.isRegistered = true;

        // full refresh to synchronize back and front end
        this.refresh();
      },
      error: (err) => {
        console.error('Registration failed:', err);
        alert('Could not register to the event. It might be full!');
      },
    });
  }

  unsubscribe(event: any) {
    if (!confirm('Are you sure you want to unregister for this event?')) return;

    this.registrationService.unRegisterToEvent(event.eventId).subscribe({
      next: (updatedEvent) => {
        alert(`Success! You are now unregistered for: ${updatedEvent.title}`);

        // UI-update
        event.isRegistered = false;

        // full refresh to synchronize back and front end
        this.refresh();
      },
      error: (err) => {
        console.error('Unregistration failed:', err);
        alert('Could not unregister to the event. It might be a problem!');
      },
    });
  }
}
