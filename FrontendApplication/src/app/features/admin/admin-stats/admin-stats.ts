import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsService } from '../../../services/statistics';

@Component({
  selector: 'app-statistics-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-stats.html',
  styleUrl: './admin-stats.scss',
})
export class AdminStatsComponent {
  private service = inject(StatisticsService);

  statistics$ = this.service.getStatistics();

  // method to refresh
  refresh() {
    this.statistics$ = this.service.getStatistics();
  }
}
