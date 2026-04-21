import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// Adapte l'import selon où se trouve ton interface Event (celle utilisée dans EventService)

import { Event } from '../models/events';

@Injectable({
  providedIn: 'root',
})
export class EventRegistrationService {
  private http = inject(HttpClient);

  // Remplace par la vraie URL de ton backend
  private apiUrl = 'http://localhost:8080/WroclArts/events';

  /**
   * Inscrit l'utilisateur à un événement.
   * Le backend attend juste un POST sur l'URL et renvoie l'événement mis à jour.
   */
  registerToEvent(eventId: number): Observable<any> {
    // Remplace 'any' par 'Event' si tu as l'interface
    // On envoie un corps vide {} car ton backend s'en fiche pour le moment
    return this.http.post<any>(`${this.apiUrl}/${eventId}/tickets`, {});
  }
}
