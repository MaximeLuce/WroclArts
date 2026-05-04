import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SystemStatistics } from '../models/system-statistics';

@Injectable({
  providedIn: 'root',
})
export class StatisticsService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/WroclArts/admin/statistics';

  getStatistics(): Observable<SystemStatistics> {
    return this.http.get<SystemStatistics>(this.api);
  }
}
