import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organization, OrganizationPayload } from '../models/organizations';

@Injectable({
  providedIn: 'root',
})
export class OrganizationService {
  private http = inject(HttpClient);

  private api = 'http://localhost:8080/WroclArts/organizations';

  getAll(): Observable<Organization[]> {
    return this.http.get<Organization[]>(this.api);
  }

  getById(id: number): Observable<Organization> {
    return this.http.get<Organization>(`${this.api}/${id}`);
  }

  create(organization: OrganizationPayload): Observable<Organization> {
    return this.http.post<Organization>(this.api, organization);
  }

  update(id: number, organization: OrganizationPayload): Observable<Organization> {
    return this.http.put<Organization>(`${this.api}/${id}`, organization);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
