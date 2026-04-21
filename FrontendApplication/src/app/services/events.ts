import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event, EventPayload } from '../models/events';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/WroclArts/events';

  getAll(): Observable<Event[]> {
    return this.http.get<Event[]>(this.api);
  }

  getById(id: number): Observable<Event> {
    return this.http.get<Event>(`${this.api}/${id}`);
  }

  create(Event: EventPayload): Observable<Event> {
    return this.http.post<Event>(this.api, Event);
  }

  update(id: number, Event: EventPayload): Observable<Event> {
    return this.http.put<Event>(`${this.api}/${id}`, Event);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
