import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../models/events';

@Injectable({
  providedIn: 'root',
})
export class EventRegistrationService {
  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/WroclArts/events';

  registerToEvent(eventId: number): Observable<Event> {
    // body request {} for the moment ; possible additional infos to be implemented
    return this.http.post<any>(`${this.apiUrl}/${eventId}/tickets`, {});
  }

  unRegisterToEvent(eventId: number): Observable<Event> {
    // body request {} for the moment ; possible additional infos to be implemented
    return this.http.delete<any>(`${this.apiUrl}/${eventId}/tickets`, {});
  }
}
