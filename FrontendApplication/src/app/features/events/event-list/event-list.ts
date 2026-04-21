import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { EventService } from '../../../services/events';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './event-list.html',
  styleUrl: './event-list.scss',
})
export class EventListComponent {
  private service = inject(EventService);

  events$ = this.service.getAll();

  refresh() {
    this.events$ = this.service.getAll();
  }

  delete(eventId: number) {
    if (!confirm('Delete this event?')) return;

    this.service.delete(eventId).subscribe(() => {
      this.events$ = this.service.getAll();
    });
  }
}
