import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../../services/events';
import { EventPayload } from '../../../models/events';

@Component({
  selector: 'app-event-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './event-form.html',
})
export class EventFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private service = inject(EventService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  eventId?: number;

  // ✅ strongly typed form
  form = this.fb.nonNullable.group({
    eventId: [0],
    address: ['', Validators.required],
    category: ['', Validators.required],
    date: ['', Validators.required],
    numberOfPlaces: [0, Validators.required],
    price: [0, Validators.required],
    status: ['', Validators.required],
    time: ['', Validators.required],
    title: ['', Validators.required],
    type: ['', Validators.required],
  });

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.eventId = Number(id);

      this.service.getById(this.eventId).subscribe((event) => {
        this.form.patchValue({
          eventId: event.eventId,
          address: event.address,
          category: event.category,
          date: event.date,
          numberOfPlaces: event.numberOfPlaces,
          price: event.price,
          status: event.status,
          time: event.time,
          title: event.title,
          type: event.type,
        });
      });
    }
  }

  submit() {
    if (this.form.invalid) return;

    // Récupération des données brutes
    const rawData = this.form.getRawValue();

    // ✅ On extrait (et retire) eventId des données qu'on va envoyer
    const { eventId, ...data } = rawData;

    if (this.eventId) {
      // Mode édition (PUT)
      this.service.update(this.eventId, data as EventPayload).subscribe(() => {
        this.router.navigate(['/']);
      });
    } else {
      // Mode création (POST)
      this.service.create(data as EventPayload).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}
