import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organization, OrganizationPayload } from '../models/organizations';

@Injectable({
  providedIn: 'root',
})
export class OrganizationService {
  private http = inject(HttpClient);
  // L'URL pointe maintenant vers le contrôleur des organisations
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

  // Attention, dans ton Swagger tu as défini la suppression sous /admin/organizations/{orgId}/status
  // ou similaire, mais si tu as implémenté le DELETE classique, ceci fonctionnera très bien !
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
